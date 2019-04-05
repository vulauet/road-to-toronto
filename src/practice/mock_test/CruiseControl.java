package practice.mock_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CruiseControl {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int d = in.nextInt();
            int n = in.nextInt();
            double maxTime = 0;
            for (int j = 0; j < n; j++) {
                int kj = in.nextInt();
                int sj = in.nextInt();
                double tj = 1.0 * (d - kj) / sj;
                if (tj > maxTime) {
                    maxTime = tj;
                }
            }
            System.out.println("Case #" + i + ": " + d / maxTime);
        }
    }
}
