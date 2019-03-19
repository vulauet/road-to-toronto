package practice;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class FibonacciModified {
    // Complete the fibonacciModified function below.

    static BigInteger fibonacciModified(int t1, int t2, int n) {
        Map<Integer, BigInteger> cachedFibMod = new HashMap<>();
        cachedFibMod.put(1, new BigInteger(String.valueOf(t1)));
        cachedFibMod.put(2, new BigInteger(String.valueOf(t2)));
        return fibonacciModified(n, cachedFibMod);
    }

    private static BigInteger fibonacciModified(int n, Map<Integer, BigInteger> fibModCache) {
        if (!fibModCache.containsKey(n)) {
            BigInteger fibModNMinus2 = fibonacciModified(n - 2, fibModCache);
            BigInteger fibModNMinus1 = fibonacciModified(n - 1, fibModCache);
            BigInteger result = fibModNMinus2.add(fibModNMinus1.multiply(fibModNMinus1));
            fibModCache.put(n, result);
        }
        return fibModCache.get(n);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] t1T2n = scanner.nextLine().split(" ");

        int t1 = Integer.parseInt(t1T2n[0]);

        int t2 = Integer.parseInt(t1T2n[1]);

        int n = Integer.parseInt(t1T2n[2]);

        BigInteger result = fibonacciModified(t1, t2, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
