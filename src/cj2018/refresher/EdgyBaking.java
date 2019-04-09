package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EdgyBaking {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            double p = in.nextDouble();
            List<Cookie> cookies = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                double w = in.nextDouble();
                double h = in.nextDouble();
                cookies.add(new Cookie(w, h));
            }
            System.out.println("Case #" + i + ": " + solve(cookies, p));
        }
    }

    private static double solve(List<Cookie> cookies, double p) {
        throw new UnsupportedOperationException();
    }

    private static class Cookie {
        double w;
        double h;

        public Cookie(double w, double h) {
            this.w = w;
            this.h = h;
        }
    }
}
