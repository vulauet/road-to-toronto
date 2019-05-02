package practice.dp;

import org.junit.Assert;

import java.util.*;

public class BricksGame {


    static long bricksGame(int[] arr) {
        /*
         * Write your code here.
         */

        long sum = 0;
        long[] ans = new long[arr.length];
        for (int i = 0; i < 3; i++) {
            sum += arr[i];
            ans[i] = sum;
        }

        for (int i = 3; i < ans.length; i++) {
            sum += arr[i];
            long min = Collections.min(Arrays.asList(ans[i - 3], ans[i - 2], ans[i - 1]));
            ans[i] = sum - min;
        }

        for (long val : ans) {
            System.out.print(val + " ");
        }
        System.out.println();
        return ans[ans.length - 1];
    }

    public static void main(String[] args) {
        Assert.assertEquals(1001, bricksGame(new int[]{999, 1, 1, 1, 0}));
        Assert.assertEquals(999, bricksGame(new int[]{0, 1, 1, 1, 999}));

        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            int arrCount = Integer.parseInt(scanner.nextLine().trim());

            int[] arr = new int[arrCount];

            String[] arrItems = scanner.nextLine().split(" ");

            for (int arrItr = 0; arrItr < arrCount; arrItr++) {
                int arrItem = Integer.parseInt(arrItems[arrItr].trim());
                arr[arrItr] = arrItem;
            }

            long result = bricksGame(arr);
            System.out.println(result);
        }
    }
}
