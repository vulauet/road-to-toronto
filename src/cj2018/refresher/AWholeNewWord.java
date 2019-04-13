package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class AWholeNewWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int l = in.nextInt();

            Set<String> inputs = new HashSet<>();
            for (int j = 0; j < n; j++) inputs.add(in.next());
            System.out.println("Case #" + i + ": " + solve(inputs, l));
        }
    }

    private static String solve(Set<String> inputs, int l) {

        return "-";
    }

    private static class Node {
        int index;
        char val;
        Set<Character> sequelCharSet;

        public Node(int index, char val) {
            this.index = index;
            this.val = val;
            this.sequelCharSet = new HashSet<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index &&
                    val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, val);
        }

        public void addSequelChar(char c) {
            sequelCharSet.add(c);
        }
    }
}
