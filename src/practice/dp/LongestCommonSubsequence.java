package practice.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestCommonSubsequence {

    static int[] longestCommonSubsequence(int[] a, int[] b) {
        List<Integer>[][] commonSubsequence = new ArrayList[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    if (i * j > 0) {
                        commonSubsequence[i][j] = new ArrayList<>(commonSubsequence[i - 1][j - 1]);
                        commonSubsequence[i][j].add(b[j]);
                    } else {
                        commonSubsequence[i][j] = new ArrayList<>(Collections.singleton(b[j]));
                    }
                } else if (i * j > 0) {
                    List<Integer> prev = commonSubsequence[i][j - 1].size() > commonSubsequence[i - 1][j].size() ?
                            commonSubsequence[i][j - 1] : commonSubsequence[i - 1][j];
                    commonSubsequence[i][j] = new ArrayList<>(prev);
                } else if (j > 0) {
                    commonSubsequence[i][j] = new ArrayList<>(commonSubsequence[i][j - 1]);
                } else if (i > 0) {
                    commonSubsequence[i][j] = new ArrayList<>(commonSubsequence[i - 1][j]);
                } else {
                    commonSubsequence[i][j] = new ArrayList<>();
                }
            }
        }

        int maxLength = 0;
        List<Integer> lcs = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (commonSubsequence[i][j].size() > maxLength) {
                    maxLength = commonSubsequence[i][j].size();
                    lcs = commonSubsequence[i][j];
                }
            }
        }

        int[] res = new int[maxLength];
        for (int i = 0; i < lcs.size(); i++) {
            res[i] = lcs.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] actuals = longestCommonSubsequence(
                new int[]{1, 2, 3, 4, 1},
                new int[]{3, 4, 1, 2, 1, 3}
        );
        printResult(actuals);
        Assert.assertArrayEquals(new int[]{1, 2, 3}, actuals);

        actuals = longestCommonSubsequence(
                new int[]{3, 9, 8, 3, 9, 7, 9, 7, 0},
                new int[]{3, 3, 9, 9, 9, 1, 7, 2, 0, 6}
        );
        printResult(actuals);
        Assert.assertArrayEquals(new int[]{3, 3, 9, 9, 7, 0}, actuals);
    }

    public static void printResult(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
