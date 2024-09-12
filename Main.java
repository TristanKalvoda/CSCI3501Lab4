import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s[] = scanner.nextLine().split(" ");
        scanner.close();
        System.out.println(Arrays.toString(s));
        int B = Integer.parseInt(s[0]);
        int N = Integer.parseInt(s[1]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s[i + 2]);
        }
        System.out.println(Arrays.toString(arr));

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

        // // Sort items in decreasing order
        // Arrays.sort(items);
        // for (int i = 0; i < items.length / 2; i++) {
        // int temp = items[i];
        // items[i] = items[items.length - i - 1];
        // items[items.length - i - 1] = temp;
        // }
    }
}