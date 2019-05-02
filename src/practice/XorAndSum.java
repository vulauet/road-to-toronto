package practice;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class XorAndSum {

    private static final BigInteger MODULO        = new BigInteger("1000000007");
    private static final BigInteger TWO           = new BigInteger("2");
    private static final BigInteger TWO_TO_314160 = TWO.pow(314160);
    private static final BigInteger MULTI_FACTOR  = BigInteger.valueOf(314160);

    static BigInteger xorAndSum(String a, String b) {
        /*
         * Write your code here.
         */
        return fastXorSum(a, b);
    }

    private static BigInteger and(String a, String b) {
        Map<Integer, Integer> accuCount = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        int accu = 0;
        for (int i = b.length() - 1; i >= b.length() - Math.min(a.length(), b.length()); i--) {
            if (b.charAt(i) == '1') accu++;
            accuCount.put(b.length() - i - 1, accu);
        }

        for (int i = a.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '1') {
                int pos = a.length() - i - 1;
                count.put(pos, accuCount.getOrDefault(pos, accu));
            }
        }

        return count.keySet().stream()
                .map(i -> TWO.pow(i).multiply(BigInteger.valueOf(count.get(i))))
                .reduce(BigInteger::add)
                .get()
                .multiply(TWO)
                .mod(MODULO);
    }

    private static BigInteger fastXorSum(String a, String b) {
        BigInteger decA = new BigInteger(a, 2);
        BigInteger decB = new BigInteger(b, 2);
        BigInteger result =
                decB.multiply(TWO_TO_314160.subtract(new BigInteger("1")))
                        .add(decA.multiply(MULTI_FACTOR)).mod(MODULO);
        result = result.subtract(and(a, b));
        return result.mod(MODULO);
    }

    private static BigInteger simpleXorSum(String a, String b) {
        int length = a.length() - (b.length() - b.lastIndexOf("1") - 2);

        BigInteger decA = new BigInteger(a, 2);
        BigInteger decB = new BigInteger(b, 2);
        BigInteger xorSum = new BigInteger("0");

        for (int i = 0; i < length; i++) {
            BigInteger xor = decA.xor(decB.shiftLeft(i));
            xorSum = xorSum.add(xor);
        }

        BigInteger multiplyTerm = TWO_TO_314160.subtract(TWO.pow(length));
        BigInteger addTerm =
                decA.multiply(BigInteger.valueOf(314160 - length)).mod(MODULO);
        xorSum = xorSum.add(decB.multiply(multiplyTerm).add(addTerm));
        return xorSum.mod(MODULO);
    }

    public static void main(String[] args) throws IOException {
        Assert.assertEquals(new BigInteger("489429555"), xorAndSum("10", "1010"));
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        String a = in.readLine().trim();
        String b = in.readLine().trim();

        long startTime = System.currentTimeMillis();
        System.out.println(fastXorSum(a, b));
        System.out.println(
                "Elapsed time " + (System.currentTimeMillis() - startTime) / 1000 + "s");
        startTime = System.currentTimeMillis();
        System.out.println(simpleXorSum(a, b));
        System.out.println(
                "Elapsed time " + (System.currentTimeMillis() - startTime) / 1000 + "s");
//        BigInteger result = xorAndSum(a, b);
//        System.out.println(result);
        in.close();
    }
}
