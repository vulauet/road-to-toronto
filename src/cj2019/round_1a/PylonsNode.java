package cj2019.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PylonsNode {

    static class Node {
        int x;
        int y;
        Set<Node> neighbors;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.neighbors = new HashSet<>();
        }

        void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

        boolean isNeighbor(Node next) {
            return this.x != next.x
                    && this.y != next.y
                    && this.x - this.y != next.x - next.y
                    && this.x + this.y != next.x + next.y
                    ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return x + 1 +
                    " " + (y + 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            int c = in.nextInt();
            System.out.println("Case #" + i + ": " + solve(r, c));
        }
    }

    public static String solve(int r, int c) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                nodes.add(new Node(i, j));

        for (int i = 0; i < nodes.size() - 1; i++) {
            Node ithNode = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node jthNode = nodes.get(j);
                if (jthNode.isNeighbor(ithNode)) {
                    ithNode.addNeighbor(jthNode);
                    jthNode.addNeighbor(ithNode);
                }
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            List<Node> traveled = new ArrayList<>(Collections.singletonList(nodes.get(i)));
            List<Node> path = solve(traveled, nodes.size());
            if (path != null) return constructPath(path);
        }
        return "IMPOSSIBLE";
    }

    private static List<Node> solve(List<Node> traveled, int size) {
        if (size == traveled.size()) return traveled;
        int curSize = traveled.size();
        Node curNode = traveled.get(curSize - 1);
        for (Node neighbor : curNode.neighbors) {
            if (!traveled.contains(neighbor)) {
                traveled.add(neighbor);
                List<Node> path = solve(traveled, size);
                if (path != null) return path;
                else traveled = traveled.subList(0, curSize);
            }
        }
        return null;
    }

    private static String constructPath(List<Node> path) {
        StringBuilder sb = new StringBuilder();
        sb.append("POSSIBLE").append('\n');
        for (Node node : path) {
            sb.append(node.x + 1).append(" ").append(node.y + 1).append('\n');
        }
        return sb.substring(0, sb.length() - 1);
    }
}
