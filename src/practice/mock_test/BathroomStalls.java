package practice.mock_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BathroomStalls {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = in.nextInt();
//            int[] result = solve(n, k);
            System.out.println("Case #" + i + ": " + (n + k) / (k + 1) + " " + (n - k) / (k + 1));
        }
    }

    private static int[] solve(int n, int k) {

        return new int[0];
    }
}
