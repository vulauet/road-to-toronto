package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitParty {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            long b = in.nextLong();
            int c = in.nextInt();
            List<Cashier> cashiers = new ArrayList<>();
            for (int j = 0; j < c; j++) {
                int m = in.nextInt();
                int s = in.nextInt();
                int p = in.nextInt();
                cashiers.add(new Cashier(m, s, p));
            }
            System.out.println("Case #" + i + ": " + solve(r, b, cashiers));
        }
    }

    private static long solve(int r, long b, List<Cashier> cashiers) {
        int maxS = cashiers.stream().mapToInt(Cashier::getS).max().getAsInt();
        int maxP = cashiers.stream().mapToInt(Cashier::getP).max().getAsInt();

        long hi = maxS * b + maxP;
        long lo = 1;
        long mid = (lo + hi) / 2;

        while (lo < hi) {
            if (doable(r, b, cashiers, mid)) hi = mid;
            else lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        return hi;
    }

    private static boolean doable(int r, long b, List<Cashier> cashiers, long t) {
        List<Long> capacity = cashiers.stream()
                                      .mapToLong(c -> Math.max(0, Math.min(c.m, (t - c.p) / c.s)))
                                      .boxed()
                                      .sorted(Comparator.reverseOrder())
                                      .collect(Collectors.toList());

        long sumCapacity = IntStream.range(0, r).boxed().mapToLong(capacity::get).sum();
        return sumCapacity >= b;
    }

    private static class Cashier {
        int m;
        int s;
        int p;

        public Cashier(int m, int s, int p) {
            this.m = m;
            this.s = s;
            this.p = p;
        }

        public int getS() {
            return s;
        }

        public int getP() {
            return p;
        }
    }
}
