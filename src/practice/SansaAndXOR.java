package practice;

import org.junit.Assert;

public class SansaAndXOR {
    static long sansaXor(int[] arr) {
        long result = 0;
        if (arr.length % 2 == 0) return 0;
        for (int i = 0; i < arr.length; i += 2) {
            result ^= arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, sansaXor(new int[]{1, 2, 3}));
        Assert.assertEquals(0, sansaXor(new int[]{4, 5, 7, 5}));
        Assert.assertEquals(110, sansaXor(new int[]{98, 74, 12}));
        Assert.assertEquals(48, sansaXor(new int[]{50, 13, 2}));
    }
}
