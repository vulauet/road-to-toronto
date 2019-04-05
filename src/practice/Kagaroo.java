package practice;

import org.junit.Assert;

public class Kagaroo {
    static String kangaroo(int x1, int v1, int x2, int v2) {
//        return simpleKagaroo(x1, v1, x2, v2);
        return brainHackedKagaroo(x1, v1, x2, v2);
    }

    private static String brainHackedKagaroo(int x1, int v1, int x2, int v2) {
        return v2 < v1 && (x1 - x2) % (v2 - v1) == 0 ? "YES" : "NO";
    }

    private static String simpleKagaroo(int x1, int v1, int x2, int v2) {
        int posDiff = x2 - x1;
        int velocityDiff = v2 - v1;
        return posDiff * velocityDiff < 0
                       && Math.abs(posDiff) % Math.abs(velocityDiff) == 0 ?
               "YES" : "NO";
    }

    public static void main(String[] args) {
//        Assert.assertEquals("NO", kangaroo(5, 3, 0, 2));
        Assert.assertEquals("NO", kangaroo(0, 2, 5, 3));
        Assert.assertEquals("YES", kangaroo(0, 3, 4, 2));
    }
}
