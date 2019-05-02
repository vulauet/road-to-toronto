package practice.dp;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Assert.assertEquals(3, lis(new int[]{3, 10, 2, 1, 20}));
        Assert.assertEquals(1, lis(new int[]{3, 2}));
        Assert.assertEquals(4, lis(new int[]{50, 3, 10, 7, 40, 80}));
    }

    private static int lis(int[] sequence) {
        Integer[] L = new Integer[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            L[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if (sequence[j] < sequence[i]) {
                    L[i] = Math.max(L[i], L[j] + 1);
                }
            }
        }
        return Collections.max(Arrays.asList(L));
    }
}
