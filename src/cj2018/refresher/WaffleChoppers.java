package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaffleChoppers {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            int c = in.nextInt();
            int h = in.nextInt();
            int v = in.nextInt();
            int[][] cake = new int[r][c];
            for (int j = 0; j < r; j++) {
                String row = in.next();
                for (int k = 0; k < c; k++)
                    if (row.charAt(k) == '@') cake[j][k] = 1;
                    else cake[j][k] = 0;
            }
//            solve(r, c, h, v, cake);
            System.out.println("Case #" + i + ": " + solve(r, c, h, v, cake));
        }
    }

    private static String solve(int r, int c, int h, int v, int[][] cake) {
        List<Integer> chipCountByRow = new ArrayList<>();
        List<Integer> chipCountByCol = new ArrayList<>();
        int totalChipCount = 0;
        for (int[] row : cake) {
            int accuRowChip = 0;
            for (int i = 0; i < row.length; i++) {
                accuRowChip += row[i];
                if (chipCountByCol.size() == i) {
                    chipCountByCol.add(row[i]);
                } else {
                    chipCountByCol.set(i, chipCountByCol.get(i) + row[i]);
                }
            }
            chipCountByRow.add(accuRowChip);
            totalChipCount += accuRowChip;
        }

        if (totalChipCount == 0) return "POSSIBLE";
        if (totalChipCount % ((h + 1) * (v + 1)) != 0) return "IMPOSSIBLE";
        List<Integer> horizontalCutPoints = divide(h, chipCountByRow, totalChipCount);
//        CollectionLogger.print(horizontalCutPoints);
        if (horizontalCutPoints.isEmpty()) return "IMPOSSIBLE";
        List<Integer> verticalCutPoints = divide(v, chipCountByCol, totalChipCount);
//        CollectionLogger.print(verticalCutPoints);

        if (verticalCutPoints.isEmpty()) return "IMPOSSIBLE";
        int chipCountPerPiece = totalChipCount / ((h + 1) * (v + 1));

        for (int i = 0; i < horizontalCutPoints.size() - 1; i++) {
            for (int j = 0; j < verticalCutPoints.size() - 1; j++) {
                int sum = 0;
                for (int k = horizontalCutPoints.get(i); k < horizontalCutPoints.get(i + 1); k++) {
                    for (int l = verticalCutPoints.get(j); l < verticalCutPoints.get(j + 1); l++) {
                        sum += cake[k][l];
                        if (sum > chipCountPerPiece) return "IMPOSSIBLE";
                    }
                }

            }
        }
        return "POSSIBLE";
    }

    private static List<Integer> divide(int cut, List<Integer> chipCount, int totalChipCount) {
        if (totalChipCount % (cut + 1) != 0) return new ArrayList<>();
        int chipCountPerPart = totalChipCount / (cut + 1);
        int accuChipCount = 0;
        List<Integer> cutPoints = new ArrayList<>();
        cutPoints.add(0);
        for (int i = 0; i < chipCount.size(); i++) {
            accuChipCount += chipCount.get(i);
            if (accuChipCount > chipCountPerPart) return new ArrayList<>();
            if (accuChipCount == chipCountPerPart) {
                cutPoints.add(i + 1);
                accuChipCount = 0;
            }
        }
        if (cutPoints.size() > cut + 2) return new ArrayList<>();
//        cutPoints.add(chipCount.size());
        return cutPoints;
    }
}
