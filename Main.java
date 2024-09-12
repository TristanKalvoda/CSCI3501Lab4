import java.util.*;

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

        // Sort arr in decreasing order
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> unpackedItems = new ArrayList<>();

        // First-Fit Decreasing Algorithm
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
        System.out.println("Unpacked items: " + unpackedItems);

        return bins;

    }
}