package practice.dp;

import org.junit.Assert;

import java.util.*;

public class BricksGame {


    static long bricksGame(int[] arr) {
        /*
         * Write your code here.
         */

        return bigBricksGame(arr);
//        return smallBricksGame(arr);
    }

    private static long bigBricksGame(int[] arr) {
        long[] maxPerTurn = new long[arr.length];
        long accumulate = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            accumulate += arr[i];
            if (i >= arr.length - 3) {
                maxPerTurn[i] = accumulate;
            } else {
                maxPerTurn[i] = accumulate - Collections.min(Arrays.asList(
                        maxPerTurn[i + 1],
                        maxPerTurn[i + 2],
                        maxPerTurn[i + 3]
                ));
            }
        }
        return maxPerTurn[0];
    }

    private static long smallBricksGame(int[] arr) {
        long sum = 0;
        for (int i : arr) {
            sum += i;
        }

        return smallBricksGame(arr, sum, 0);
    }

    private static long smallBricksGame(int[] arr, long sum, int start) {
        if (start + 3 >= arr.length) return sum;
        return sum - Collections.min(Arrays.asList(
                smallBricksGame(arr, sum - arr[start], start + 1),
                smallBricksGame(arr, sum - arr[start] - arr[start + 1], start + 2),
                smallBricksGame(arr, sum - arr[start] - arr[start + 1] - arr[start + 2],
                        start + 3)
        ));
    }

    public static void main(String[] args) {
        Assert.assertEquals(1001, bricksGame(new int[]{999, 1, 1, 1, 0}));
        Assert.assertEquals(999, bricksGame(new int[]{0, 1, 1, 1, 999}));

//        Scanner scanner = new Scanner(System.in);
//        int t = Integer.parseInt(scanner.nextLine().trim());
//
//        for (int tItr = 0; tItr < t; tItr++) {
//            int arrCount = Integer.parseInt(scanner.nextLine().trim());
//
//            int[] arr = new int[arrCount];
//
//            String[] arrItems = scanner.nextLine().split(" ");
//
//            for (int arrItr = 0; arrItr < arrCount; arrItr++) {
//                int arrItem = Integer.parseInt(arrItems[arrItr].trim());
//                arr[arrItr] = arrItem;
//            }
//
//            long result = bricksGame(arr);
//            System.out.println(result);
//        }
    }
}
