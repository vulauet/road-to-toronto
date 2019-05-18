package cj2019.round_1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PowerArrangers {
    private static char[] ALPHABET = new char[]{'A', 'B', 'C', 'D', 'E'};

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int f = in.nextInt();
        for (int i = 0; i < t; ++i) {
            solveSmall(in);
            String result = in.next();
            if (result.equals("N")) System.exit(1);
        }

    }

    private static void solveSmall(Scanner in) {
        char[] figures = new char[595];
        Set<Character> contained = new HashSet<>();
        StringBuilder res = new StringBuilder();
        Map<Character, List<Integer>> charToIndices = new HashMap<>();

        for (int i = 1; i < 595; i+=5)
            queryIndex(in, figures, charToIndices, i);
        findNextChar(contained, res, 24, charToIndices);

        List<Integer> toExamine = charToIndices.get(res.charAt(res.length() - 1));
        charToIndices = nextChar(in, figures, contained, res, toExamine, 6);
        toExamine = charToIndices.get(res.charAt(res.length() - 1));
        charToIndices = nextChar(in, figures, contained, res, toExamine, 2);
        toExamine = charToIndices.get(res.charAt(res.length() - 1));
        charToIndices = new HashMap<>();

        for (Integer i : toExamine) {
            int nextIndex = i + 1;
            queryIndex(in, figures, charToIndices, nextIndex);
        }
        for (char c : ALPHABET) {
            if (!contained.contains(c) && !charToIndices.containsKey(c)) {
                res.append(c);
                contained.add(c);
                break;
            }
        }
        for (Character c : ALPHABET) {
            if (!contained.contains(c)) {
                res.append(c);
                break;
            }
        }

        System.out.println(res.toString());
    }

    private static Map<Character, List<Integer>> nextChar(
            Scanner in,
            char[] figures,
            Set<Character> contained,
            StringBuilder res,
            List<Integer> toExamine,
            int limit
    ) {
        Map<Character, List<Integer>> charToIndices = new HashMap<>();
        for (Integer i : toExamine) {
            int nextIndex = i + 1;
            queryIndex(in, figures, charToIndices, nextIndex);
        }
        findNextChar(contained, res, limit, charToIndices);
        return charToIndices;
    }

    private static void findNextChar(
            Set<Character> contained,
            StringBuilder res,
            int limit,
            Map<Character, List<Integer>> charToIndices
    ) {
        for (Character character : charToIndices.keySet()) {
            if (charToIndices.get(character).size() < limit) {
                res.append(character);
                contained.add(character);
                break;
            }
        }
    }

    protected static void queryIndex(
            Scanner in,
            char[] figures,
            Map<Character, List<Integer>> charToIndices,
            int index
    ) {
        System.out.println(index);
        figures[index] = in.next().charAt(0);
        List<Integer> indices =
                charToIndices.getOrDefault(figures[index], new ArrayList<>());
        indices.add(index);
        charToIndices.put(figures[index], indices);
    }
}
