package practice;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class Contacts {

    static List<Integer> contactsString(String[][] queries) {
        List<Integer> results = new ArrayList<>();
        List<String> contacts = new ArrayList<>();
        for (String[] query : queries) {
            switch (query[0]) {
                case "add":
                    contacts.add(query[1]);
                    break;
                case "find":
                    int count = (int) contacts.stream()
                            .filter(contact -> contact.startsWith(query[1]))
                            .count();
                    results.add(count);
                    break;
            }
        }
        return results;
    }

    static int[] contacts(String[][] queries) {
        /*
         * Write your code here.
         */

        TrieContacts contacts = new TrieContacts();
        int countFindPartial =
                (int) Stream.of(queries)
                        .filter(query -> query[0].equals("find"))
                        .count();


        int[] results = new int[countFindPartial];
        int walk = 0;
        for (String[] query : queries) {
            switch (query[0]) {
                case "add":
                    contacts.add(query[1]);
                    break;
                case "find":
                    results[walk++] = contacts.findPartial(query[1]);
                    break;
            }
        }
        return results;
    }

    private static class Node {
        Node[] next = new Node[26];
        String value;
        int size = 0;
    }

    private static class TrieContacts {

        private Node root = new Node();

        void add(String name) {
            this.root = add(root, name, 0);
        }

        private Node add(Node node, String name, int index) {
            if (node == null) node = new Node();
            node.size += 1;
            if (index == name.length()) {
                node.value = "";
                return node;
            }

            char c = name.charAt(index);
            node.next[c] = add(node.next[c], name, index + 1);
            return node;
        }

        int findPartial(String partial) {
            Node walk = root;
            for (int i = 0; i < partial.length(); i++) {
                walk = walk.next[partial.charAt(i)];
                if (walk == null) return 0;
            }
            return walk.size;
        }

        private int loopCount(Node walk) {
            int sum = 0;
            Stack<Node> s = new Stack<>();
            s.push(walk);
            while (!s.isEmpty()) {
                Node top = s.pop();
                if (top.value != null) sum++;
                for (Node node : top.next) {
                    if (node != null) {
                        s.push(node);
                    }
                }
            }
            return sum;
        }

        private int recursiveCount(Node walk) {
            int sum = walk.value == null ? 0 : 1;
            for (int i = 0; i < walk.next.length; i++) {
                if (walk.next[i] != null) {
                    sum += loopCount(walk.next[i]);
                }
            }
            return sum;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Assert.assertArrayEquals(new int[]{2, 0},
                contacts(new String[][]{
                        {"add", "hack"},
                        {"add", "hackerrank"},
                        {"find", "hac"},
                        {"find", "hak"}
                }));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int queriesRows = Integer.parseInt(in.readLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = in.readLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            System.out.println(String.valueOf(result[resultItr]));

        }
    }
}
