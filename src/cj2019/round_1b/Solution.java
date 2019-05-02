package cj2019.round_1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int w = in.nextInt();
        for (int i = 0; i < t; ++i) {
            solveSmall(w, in);
            int result = in.nextInt();
            if (result == -1) System.exit(1);
        }

    }

    private static void solveSmall(int w, Scanner in) {
        int[] responds = new int[w];
        for (int i = 0; i < w; i++) {
            System.out.println(i + 1);
            responds[i] = in.nextInt();
        }

        long[] result = new long[6];
        result[3] = (responds[4] - responds[5] / 2) / 2;
        result[4] = (responds[4] - responds[5] / 2) - result[3];
        result[0] = ((responds[4] - responds[3]) - result[3]) / 16;
        result[1] = (responds[1] - responds[0]) - 2 * result[0];
        result[2] = responds[2] - responds[1] - 4 * result[0];
        result[5] = responds[0] - 2 * result[0] - result[1] - result[2] - result[3] - result[4];

        StringBuilder sb = new StringBuilder();
        for (long res : result) {
            sb.append(res).append(" ");
        }
        System.out.println(sb.toString());
    }
}
