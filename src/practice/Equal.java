package practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Equal {
    // Complete the equal function below.
    static int equal(int[] arr) {
        int minElem = Arrays.stream(arr).min().getAsInt();
        return Collections.min(
                Arrays.asList(
                        fMin(arr, minElem),
                        fMin(arr, minElem - 1),
                        fMin(arr, minElem - 2),
                        fMin(arr, minElem - 3),
                        fMin(arr, minElem - 4)
                )
        );
    }

    private static int fMin(int[] arr, int minElem) {
        return Arrays.stream(arr)
                .boxed()
                .mapToInt(elem -> elem - minElem)
                .map(Equal::downToZero)
                .sum();
    }

    private static int downToZero(int diff) {
        int result = diff / 5;
        diff = diff % 5;
        result += diff / 2;
        diff = diff % 2;
        result += diff;
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int result = equal(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
