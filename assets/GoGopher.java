//package cj2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class GoGopher {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int A = in.nextInt();
            Set<Cell> deployed = new HashSet<>();
            int a = A;
            int b = 1;
            int minDiff = a - b;
            for (int j = 2; j < A / 2; j++) {
                if (A % j == 0 && Math.abs(A - 2 * j) < minDiff) {
                    a = j;
                    b = A / j;
                    minDiff = Math.abs(A - 2 * j);
                }
            }

            List<Integer> xAxis = findPoint(a);
            List<Integer> yAxis = findPoint(b);

            solve(in, deployed, xAxis, yAxis);
        }
    }

    private static void solve(Scanner in, Set<Cell> deployed, List<Integer> xAxis, List<Integer> yAxis) {
        int deployCount = 0;
        for (Integer x : xAxis) {
            for (Integer y : yAxis) {
                while (!fullyDeployed(deployed, x, y)) {
                    System.out.println(x + " " + y);
                    ++deployCount;
                    int row = in.nextInt();
                    int col = in.nextInt();
                    if (row == 0 && col == 0 || deployCount > 1000) {
                        return;
                    }
                    deployed.add(new Cell(row, col));
                }
            }
        }
    }

    private static boolean fullyDeployed(Set<Cell> deployed, Integer x, Integer y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!deployed.contains(new Cell(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Integer> findPoint(int a) {
        if (a < 3) return Collections.singletonList(2);
        List<Integer> xAxis = new ArrayList<>();
        for (int j = 2; j < a; j += 3) {
            xAxis.add(j);
        }

        if (a - xAxis.get(xAxis.size() - 1) > 1) {
            xAxis.add(a - 1);
        }
        return xAxis;
    }

    private static class Cell {
        private int row;
        private int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row &&
                    col == cell.col;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, col);
        }
    }
}
