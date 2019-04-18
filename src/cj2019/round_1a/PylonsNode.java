package cj2019.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PylonsNode {

    static class Node {
        int x;
        int y;

        boolean isNeighbor(Node next) {
            return this.x != next.x
                    && this.y != next.y
                    && this.x - this.y != next.x - next.y
                    && this.x + this.y != next.x + next.y
                    ;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                nodes.add(new Node(i, j));
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            List<Node> available = Stream.concat(
                    nodes.subList(0, i).stream(),
                    nodes.subList(i + 1, nodes.size()).stream()
            ).collect(Collectors.toList());
            List<Node> path = solve(Collections.singletonList(nodes.get(i)), available);
            if (path != null) {
                return constructPath(path);
            }
        }
        return "IMPOSSIBLE";
    }

    private static String constructPath(List<Node> path) {
        StringBuilder sb = new StringBuilder();
        sb.append("POSSIBLE").append('\n');
        for (Node node : path) {
            sb.append(node.x + 1).append(" ").append(node.y + 1).append('\n');
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static List<Node> solve(List<Node> traveled, List<Node> nodes) {
        if (nodes.isEmpty()) return traveled;
        Node curNode = traveled.get(traveled.size() - 1);
        for (int i = 0; i < nodes.size(); i++) {
            if (curNode.isNeighbor(nodes.get(i)) && !traveled.contains(nodes.get(i))) {

                List<Node> available = Stream.concat(
                        nodes.subList(0, i).stream(),
                        nodes.subList(i + 1, nodes.size()).stream()
                ).collect(Collectors.toList());

                List<Node> newPath = new ArrayList<>(traveled);
                newPath.add(nodes.get(i));
                List<Node> path = solve(newPath, available);
                if (path != null) return path;
            }
        }
        return null;
    }
}
