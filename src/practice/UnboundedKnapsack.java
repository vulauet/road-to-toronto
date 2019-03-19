package practice;

import org.junit.Assert;

import java.util.Arrays;

public class UnboundedKnapsack {
    static int unboundedKnapsack(int k, int[] arr) {
        arr = Arrays.stream(arr).distinct().toArray();
        return k - remainCapacity(k, arr, 0);
    }

    private static int remainCapacity(int k, int[] arr, int i) {
        if (k < 0) { return k + arr[i];}
        else {
            int minimalRemaining = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                int remainAfterTakeJ = remainCapacity(k - arr[j], arr, j);
                if (remainAfterTakeJ < minimalRemaining) {
                    minimalRemaining = remainAfterTakeJ;
                }
            }
            return minimalRemaining;
        }
    }

    public static void main(String[] args) {
        Assert.assertEquals(12, unboundedKnapsack(12, new int[]{1, 6, 9}));
        Assert.assertEquals(9, unboundedKnapsack(9, new int[]{3, 4, 4, 4, 8}));
    }
}
