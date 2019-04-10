package cj2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ForegoneSolution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            String n = in.next();
            List<BigInteger> result = solve(n);
            System.out.println("Case #" + i + ": " + result.get(0) + " " + result.get(1));
        }
    }

    private static List<BigInteger> solve(String n) {
        StringBuilder sb = new StringBuilder();
        String placeHolder = "";
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '4') {
                sb.append('1');
                placeHolder = "0";
            } else {
                sb.append(placeHolder);
            }
        }
        BigInteger small = new BigInteger(sb.toString());
        return Arrays.asList(small, new BigInteger(n).subtract(small));
    }
}
