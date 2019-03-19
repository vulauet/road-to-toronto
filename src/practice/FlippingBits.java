package practice;

import org.junit.Assert;

public class FlippingBits {

    private static long flippingBits(long n) {
        return (1L << 32) - n - 1;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2147483648L, flippingBits(2147483647));
        Assert.assertEquals(4294967294L, flippingBits(1));
        Assert.assertEquals(4294967295L, flippingBits(0));
        Assert.assertEquals(4294967291L, flippingBits(4));
        Assert.assertEquals(4294843839L, flippingBits(123456));
    }
}
