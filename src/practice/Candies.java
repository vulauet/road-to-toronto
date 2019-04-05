package practice;

import org.junit.Assert;

import java.util.stream.IntStream;

public class Candies {
    static long candies(int[] arr) {
        int n = arr.length;
        int[] left2Right = new int[n];
        int[] right2Left = new int[n];
        left2Right[0] = 1;
        right2Left[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) left2Right[i] = left2Right[i - 1] + 1;
            else left2Right[i] = 1;

            if (arr[n - i - 1] > arr[n - i]) right2Left[n - i - 1] = right2Left[n - i] + 1;
            else right2Left[n - i - 1] = 1;
        }

        return IntStream.range(0, n).mapToLong(i -> Math.max(left2Right[i], right2Left[i])).sum();
    }

    public static void main(String[] args) {
        Assert.assertEquals(19, candies(new int[]{2, 4, 2, 6, 1, 7, 8, 9, 2, 1}));
        Assert.assertEquals(12, candies(new int[]{2, 4, 3, 5, 2, 6, 4, 5}));
        Assert.assertEquals(4, candies(new int[]{1, 2, 2}));
    }
}
