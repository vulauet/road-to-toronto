package cj2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int b = in.nextInt();
            int f = in.nextInt();
            List<Integer> brokenWorkers = solve(n, b, in);
            StringBuilder sb = new StringBuilder();
            for (Integer brokenWorker : brokenWorkers) sb.append(brokenWorker).append(" ");
            System.out.println("Case #" + i + ": " + sb.toString());
        }
    }

    private static List<Integer> solve(int n, int b, Scanner responses) {
        PriorityQueue<DefectRange> pq = new PriorityQueue<>(Comparator.comparingInt(DefectRange::getLo));
        DefectRange initRange = new DefectRange(0, n, b);
        pq.add(initRange);
        List<Integer> results = new ArrayList<>();
        while (!pq.isEmpty()) {

            StringBuilder sb = new StringBuilder();
            int bit = 1;
            while (!pq.isEmpty()) {
                DefectRange defectRange = pq.poll();
                for (int i = sb.length(); i < defectRange.lo; i++) sb.append(bit);
                bit = flipBit(bit);
                for (int i = defectRange.lo; i < defectRange.hi; i++) {
                    sb.append(bit);
                    if ((i - defectRange.lo) % defectRange.defectCount == 0) {
                        bit = flipBit(bit);
                    }
                }
                bit = flipBit(bit);
            }
            String msg = sb.toString();
            System.out.println(msg);
            String resp = responses.next();
            processResponse(msg, resp, pq, results);
        }


    }

    private static void processResponse(
            String msg,
            String resp,
            PriorityQueue<DefectRange> pq,
            List<Integer> results
    ) {
        String[] msgSplit = splitMsg(msg);
        String[] rspSplit = splitMsg(resp);
        int msgCursor = 0;
        int rspCursor = 0;

        while (msgCursor < msgSplit.length) {

        }
    }

    private static String[] splitMsg(String msg) {
        return msg.replaceAll("01", "0 1").replaceAll("10", "1 0").split(" ");
    }

    private static int flipBit(int bit) {
        return 1 - bit;
    }

    private static class DefectRange {
        int lo;
        int hi;
        int defectCount;

        DefectRange(int lo, int hi, int defectCount) {
            this.lo = lo;
            this.hi = hi;
            this.defectCount = defectCount;
        }

        public int getLo() {
            return lo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DefectRange that = (DefectRange) o;
            return lo == that.lo &&
                    hi == that.hi &&
                    defectCount == that.defectCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lo, hi, defectCount);
        }
    }
}
