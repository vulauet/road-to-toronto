package practice;

import java.math.BigInteger;

public class ExtraLongFactorials {

    static void extraLongFactorials(int n) {
        BigInteger res = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            res = res.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println(res);

    }

    public static void main(String[] args) {

    }
}
