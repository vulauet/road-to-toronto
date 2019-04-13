package cj2019.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Pylons {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            int c = in.nextInt();
            System.out.println("Case #" + i + ": " + solveSmall(r, c));
        }
    }

    public static String solveSmall(int r, int c) {
        int[][] board = new int[r][c];
        boolean solve = solve(board, r, c, 0, 0, 0);
        return solve ? printSolution(board, r, c) : "IMPOSSIBLE";
    }

    private static boolean solve(int[][] board, int r, int c, int jumpCount, int curR, int curC) {
        if (jumpCount == r * c)
            return true;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 0 && isSafe(curR, curC, i, j, jumpCount)) {
                    board[i][j] = jumpCount + 1;
                    if (solve(board, r, c, jumpCount + 1, i, j)) {
                        return true;
                    }
                    board[i][j] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isSafe(int curR, int curC, int nextR, int nextC, int jumpCount) {
        return (curR != nextR && curC != nextC
                && curR - curC != nextR - nextC
                && curR + curC != nextR + nextC)
                || jumpCount == 0;
    }

    private static String printSolution(int[][] board, int r, int c) {
        StringBuilder sb = new StringBuilder();
        sb.append("POSSIBLE").append('\n');
        int[][] path = new int[r * c][2];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                path[board[i][j] - 1][0] = i + 1;
                path[board[i][j] - 1][1] = j + 1;
            }
        }

        for (int[] step : path) sb.append(step[0]).append(" ").append(step[1]).append('\n');
        return sb.substring(0, sb.length() - 1);
    }
}
