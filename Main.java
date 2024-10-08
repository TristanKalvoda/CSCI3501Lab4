import java.util.*;

// Dylan Packer, Tristan Kalvoda

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s[] = scanner.nextLine().split(" ");
        scanner.close();
        int B = Integer.parseInt(s[0]);
        int N = Integer.parseInt(s[1]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s[i + 2]);
        }

        List<List<Integer>> bins = binApproximator(B, arr);

        // calculate unused space
        List<Integer> unusedSpace = new ArrayList<>();
        for (List<Integer> bin : bins) {
            int sum = 0;
            for (int item : bin) {
                sum += item;
            }
            unusedSpace.add(B - sum);
        }

        // Output results
        System.out.println("Bins:");
        for (List<Integer> bin : bins) {
            System.out.println(bin);
        }
        System.out.println("Unused space in each bin: " + unusedSpace);
    }

    public static List<List<Integer>> binApproximator(int B, int[] arr) {
        Arrays.sort(arr);

        // Sort arr in decreasing order. 
        // Efficiency of O(n log n)
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> unpackedItems = new ArrayList<>();

        // First-Fit Decreasing Algorithm
        // Efficiency of O(n^2)
        for (int item : arr) {
            boolean placed = false;
            for (List<Integer> bin : bins) {
                int currentLoad = bin.stream().mapToInt(Integer::intValue).sum();
                if (currentLoad + item <= B) {
                    bin.add(item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                if (bins.size() < 3) {
                    List<Integer> newBin = new ArrayList<>();
                    newBin.add(item);
                    bins.add(newBin);
                } else {
                    unpackedItems.add(item);
                }
            }
        }

        // Output unpacked items
        // Efficiency of O(n)
        System.out.println("Unpacked items: " + unpackedItems);

        return bins;

    }
    // tests
    // 10 10 9 8 7 6 5 4 3 2 1 10
    // This test shows that we have leftover items 7 6 5 4 3 
    //the output of the bins are [10] [9, 1] [8, 2]

    // 20 8 12 4 8 15 9 3 1 10
    // like the example on canvas we got 
    // [15, 4, 1] [12, 8] [10, 9] with a left over of 3

    // This test is where the limit of the list is more then the capacity
    // 20 8 12 4 8 15 9 3 1 10 8
    //we get the same thing as the previous test but with the leftover of 3 the 8 is just ignored (kind of)

    //This test is if all the items are over the capacity B
    // 10 9 12 13 14 15 16 17 18 19 20
    // This test show that the code put 20 19 18 in the bins and the unused space is all in the negative

    // This test will show what happens if the list is to small
    // 10 10 1 2 3 4 5 6
    // This throws an exception


    // The algorithm has a worst-case time complexity of (O(N^2)). 
    // This is primarily due to the nested loops in the First-Fit Decreasing Algorithm, 
    // where each item is checked against all bins, and the sum of items in each bin is recalculated. 
    // The sorting step contributes (O(N \log N)), but it is dominated by the (O(N^2)) term.
    
}