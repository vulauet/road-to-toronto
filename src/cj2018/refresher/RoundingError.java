package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundingError {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int l = in.nextInt();
            List<Integer> count = new ArrayList<>();
            for (int j = 0; j < l; j++) count.add(in.nextInt());
            System.out.println("Case #" + i + ": " + solve(n, l, count));
        }
    }

    private static int solve(int n, int l, List<Integer> count) {

        return 0;
    }
}
