package practice;

import org.junit.Assert;

import java.util.Arrays;

public class MaximumSubarray {
    static int[] maxSubarray(int[] arr) {
        int maxSequence = Arrays.stream(arr).filter(i -> i > 0).sum();
        if (maxSequence <= 0) {
            int max = Arrays.stream(arr).max().orElse(0);
            return new int[]{max, max};
        }
        int maxEndingHere = arr[0];
        int maxEndingSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxEndingSoFar = Math.max(maxEndingHere, maxEndingSoFar);
        }
        return new int[]{maxEndingSoFar, maxSequence};
    }


    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{10, 10}, maxSubarray(new int[]{1, 2, 3, 4}));
        Assert.assertArrayEquals(new int[]{10, 11},
                maxSubarray(new int[]{2, -1, 2, 3, 4, -5}));
        Assert.assertArrayEquals(new int[]{-1, -1},
                maxSubarray(new int[]{-2, -3, -1, -4, -6}));
        Assert.assertArrayEquals(new int[]{16, 20}, maxSubarray(new int[]{-1, 2, 3, -4, 5, 10}));
        Assert.assertArrayEquals(new int[]{6, 10}, maxSubarray(new int[]{-1, 2, 3, -4,
                2, 3}));
    }
}
