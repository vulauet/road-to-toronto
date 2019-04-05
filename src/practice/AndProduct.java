package practice;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AndProduct {

    static long andProduct(long a, long b) {
        return andProduct(a, b, Long.toBinaryString(a).length());
    }

    private static long andProduct(long a, long b, int aLength) {
        while (true) {
            long residual = 1L << (aLength - 1);
            if (a == 0 || b >= residual * 2) return 0;
            if (a >= residual)
                return residual + andProduct(a - residual, b - residual, aLength - 1);
            aLength -= 1;
        }
    }

    public static void main(String[] args) {

        Assert.assertEquals(12, andProduct(12, 15));
        Assert.assertEquals(2, andProduct(2, 3));
        Assert.assertEquals(8, andProduct(8, 13));
        Assert.assertEquals(16, andProduct(17, 23));
        Assert.assertEquals(8, andProduct(11, 15));
        Assert.assertEquals(360448, andProduct(364011, 366022));
        Assert.assertEquals(2621440, andProduct(2821881, 2964259));
        Assert.assertEquals(41943040, andProduct(44379234, 46222945));

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(in.readLine());
            // int n = scanner.nextInt();
            // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int nItr = 0; nItr < n; nItr++) {
                // String[] ab = scanner.nextLine().split(" ");
                String[] ab = in.readLine().split(" ");

                long a = Long.parseLong(ab[0]);

                long b = Long.parseLong(ab[1]);

                long result = andProduct(a, b);
                System.out.println(result);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
