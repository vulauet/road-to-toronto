package cj2018.round_1c;

import java.util.*;
import java.io.*;

public class AWholeNewWord {

    private List<String> words;
    private int n;
    private int l;

    public AWholeNewWord(List<String> words, int n, int l) {
        this.words = words;
        this.n = n;
        this.l = l;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int l = in.nextInt();

            List<String> words = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                words.add(in.next());
            }
            System.out.println("Case #" + i + ": " + new AWholeNewWord(words, n, l).solve());
        }
    }

    public String solve() {
        if (l == 1 || n == 1) return "-";
        if (l <= 2) return solveSmall();
        return solveBig();
    }

    private String solveBig() {
        return null;
    }

    private String solveSmall() {
        Set<Character> firstChars = new HashSet<>();
        Set<Character> secondChars = new HashSet<>();
        for (String word : words) {
            firstChars.add(word.charAt(0));
            secondChars.add(word.charAt(1));
        }
        for (Character firstChar : firstChars) {
            for (Character secondChar : secondChars) {
                String newWord = new StringBuilder().append(firstChar).append(secondChar).toString();
                if (!words.contains(newWord)) {
                    return newWord;
                }
            }
        }

        return "-";
    }
}
