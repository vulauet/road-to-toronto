package cj2019.round_1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BacterialTactics {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            int c = in.nextInt();
            int[][] grid = new int[r][c];

            for (int j = 0; j < r; j++) {
                String row = in.next();
                for (int k = 0; k < row.length(); k++) {
                    grid[j][k] = row.charAt(k) == '.' ? 0 : 1;
                }
            }

            System.out.println("Case #" + i + ": " + solve(r, c, grid));
        }
    }

    public static int solve(int r, int c, int[][] grid) {

        int result = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    result += placeVertical(r, c, grid);
                    result += placeHorizontal(r, c, grid);
                }
            }
        }

        return result;
    }

    private static int placeHorizontal(int r, int c, int[][] grid) {
        return 0;
    }

    private static int placeVertical(int r, int c, int[][] grid) {

        return 0;
    }
}
