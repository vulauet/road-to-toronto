package practice;

import org.junit.Assert;

public class GreatXor {
    static long theGreatXor(long x) {
        long count = 0;
        String binarizedX = Long.toBinaryString(x);
        for (int i = 1; i < binarizedX.length(); i++) {
            if (binarizedX.charAt(i) == '0') {
                count += 1L << (binarizedX.length() - i - 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Assert.assertEquals(1, theGreatXor(2));
        Assert.assertEquals(2, theGreatXor(5));
        Assert.assertEquals(5, theGreatXor(10));
        Assert.assertEquals(27, theGreatXor(100));
    }
}
