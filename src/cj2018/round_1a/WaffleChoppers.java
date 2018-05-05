package cj2018.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class WaffleChoppers {

    private int H;
    private int V;
    private int R;
    private int C;
    private char[][] waffle;

    public WaffleChoppers(int h, int v, char[][] waffle) {
        this.H = h;
        this.V = v;
        this.waffle = waffle;
        this.R = waffle.length;
        this.C = waffle[0].length;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int R = in.nextInt(); // num rows
            int C = in.nextInt(); // num cols
            int H = in.nextInt(); // horizontal cut
            int V = in.nextInt(); // vertical cut
            char[][] waffle = new char[R][C];
            for (int j = 0; j < R; j++) {
                String row = in.next();
                for (int k = 0; k < C; k++) {
                    waffle[j][k] = row.charAt(k);
                }
            }
            System.out.println("Case #" + i + ": " + new WaffleChoppers(H, V, waffle).answer());
        }
    }

    public String answer() {
//        if (H == 1 && V == 1) {
//            return solveSmall();
//        }
        return solveBig();
    }

    private String solveBig() {
        int numPieces = (H + 1) * (V + 1);
        int chipTotal = countChip(waffle, 0, R, 0, C);
        if (chipTotal == 0) return "POSSIBLE";
        if (chipTotal % numPieces > 0) return "IMPOSSIBLE";
        List<Integer> horizontalCuts = findHorizontalCuts(waffle, chipTotal, H);
        List<Integer> verticalCuts = findVerticalCuts(waffle, chipTotal, V);
        if (verticalCuts.size() <= 2 || horizontalCuts.size() <= 2) return "IMPOSSIBLE";

        int chipPerPiece = chipTotal / numPieces;
        for (int i = 0; i < horizontalCuts.size() - 1; i++) {
            for (int j = 0; j < verticalCuts.size() - 1; j++) {
                if (countChip(waffle, horizontalCuts.get(i), horizontalCuts.get(i + 1), verticalCuts.get(j), verticalCuts.get(j + 1)) != chipPerPiece) {
                    return "IMPOSSIBLE";
                }
            }
        }
        return "POSSIBLE";
    }

    private List<Integer> findVerticalCuts(char[][] waffle, int chipTotal, int v) {
        List<Integer> verticalCuts = new ArrayList<>();
        verticalCuts.add(0);
        int chipPerVerticalPiece = chipTotal / (v + 1);
        int chipCount = 0;
        List<Integer> chipAccumulate = new ArrayList<>();
        for (int i = 0; i < waffle[0].length; i++) {
            for (int j = 0; j < waffle.length; j++) {
                if (waffle[j][i] == '@') {
                    ++chipCount;
                }
            }
            chipAccumulate.add(chipCount);
        }
        findCutPoint(verticalCuts, chipPerVerticalPiece, chipAccumulate);
        return verticalCuts;
    }

    private void findCutPoint(List<Integer> cutPoints, int chipPerPiece, List<Integer> chipAccumulate) {
        int nextCutChipCount = chipPerPiece;
        for (int i = 0; i < chipAccumulate.size(); i++) {
            if (chipAccumulate.get(i) % nextCutChipCount == 0) {
                cutPoints.add(i + 1);
                nextCutChipCount += chipPerPiece;
            }
        }
    }

    private List<Integer> findHorizontalCuts(char[][] waffle, int chipTotal, int h) {
        List<Integer> horizontalCuts = new ArrayList<>();
        horizontalCuts.add(0);
        int chipPerHorizontalPiece = chipTotal / (h + 1);
        int chipCount = 0;
        List<Integer> chipAccumulate = new ArrayList<>();
        for (int i = 0; i < waffle.length; i++) {
            for (int j = 0; j < waffle[i].length; j++) {
                if (waffle[i][j] == '@') {
                    ++chipCount;
                }
            }
            chipAccumulate.add(chipCount);
        }
        findCutPoint(horizontalCuts, chipPerHorizontalPiece, chipAccumulate);
        return horizontalCuts;
    }

    private int countChip(char[][] waffle, int startRow, int endRow, int startCol, int endCol) {

        int chipCount = 0;
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (waffle[i][j] == '@') {
                    ++chipCount;
                }
            }
        }
        return chipCount;
    }

    private String solveSmall() {
        int chipTotal = countChip(waffle, 0, R, 0, C);
        int chipPerPiece = chipTotal / 4;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (containEnoughChip(waffle, i, j, chipPerPiece)) {
                    return "POSSIBLE";
                }
            }
        }
        return "IMPOSSIBLE";
    }

    private boolean containEnoughChip(char[][] waffle, int i, int j, int chipPerPiece) {
        return countChip(waffle, 0, i, 0, j) == chipPerPiece
                && countChip(waffle, i, waffle.length, 0, j) == chipPerPiece
                && countChip(waffle, 0, i, j, waffle[0].length) == chipPerPiece
                && countChip(waffle, i, waffle.length, j, waffle[0].length) == chipPerPiece;
    }


}
