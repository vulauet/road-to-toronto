package cj2019.qualification;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ForegoneSolution {
    public static void main(String[] args) {
//        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 4444; i <= 999999999; ++i) {
//        for (int i = 1; i <= t; ++i) {
//            int n = in.nextInt();
//        int i = 4454;
            long startTime = System.currentTimeMillis();

            int[] result = solve(i);
            Assert.assertNotEquals(0, result[0]);
            System.out.println("Case #" + i + ": " + result[0] + " " + result[1]);
            System.out.println(
                    "Elapsed time " + (System.currentTimeMillis() - startTime) / 1000 + "s");
        }
    }

    private static int[] solve(int n) {
        for (int i = 1; i < n; i++) {
            List<Integer> aDigitList = toDigit(i);
            List<Integer> bDigitList = toDigit(n - i);
            int i1 = aDigitList.indexOf(4);
            if (i1 > -1) {
                i += Math.pow(10, aDigitList.size() - i1 - 1) - 1;
                continue;
            }

            int i2 = bDigitList.indexOf(4);
            if (i2 > -1) {
                i += Math.pow(10, i2) - 1;
                continue;
            }

            return new int[]{i, n - i};

        }
        return new int[]{0, 0};
    }

    public static List<Integer> toDigit(int n) {
        List<Integer> digitList = new ArrayList<>();
        while (n > 0) {
            digitList.add(n % 10);
            n /= 10;
        }
        return digitList;
    }
}
