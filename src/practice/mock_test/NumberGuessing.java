package practice.mock_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NumberGuessing {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 1; i <= t; ++i) {
            int a = in.nextInt() + 1;
            int b = in.nextInt();
            int n = in.nextInt();
            solve(a, b, in);
        }
    }

    private static void solve(int a, int b, Scanner in) {
        int m = (a + b) / 2;
        System.out.println(m);
        String s = in.next();
        if (s.equals("CORRECT")) {
            return;
        } else if (s.equals("TOO_SMALL")) {
            solve(m + 1, b, in);
        } else {
            solve(a, m - 1, in);
        }
    }
}
