package practice;

import org.junit.Assert;

import java.util.*;

public class SnakesAndLadders {
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            Node e = new Node(i);
            nodes.add(e);
        }

        for (int[] ladder : ladders) nodes.set(ladder[0], nodes.get(ladder[1]));
        for (int[] snake : snakes) nodes.set(snake[0], nodes.get(snake[1]));

        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(1));
        Set<Node> examined = new HashSet<>();

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            if (top.value == 100) {
                break;
            } else {
                examined.add(top);

                for (int i = 1; i <= 6; i++) {
                    if (top.value + i <= 100) {
                        Node node = nodes.get(top.value + i);
                        if (!examined.contains(node) && !queue.contains(node)) {
                            node.parent = top;
                            queue.add(node);
                        }
                    }
                }
            }
        }

        if (nodes.get(100).parent == null) return -1;

        int result = 1;
        Node walk = nodes.get(100);
        while (walk.parent.value != 1) {
            ++result;
            walk = walk.parent;
            System.out.println(walk.value);
        }
        return result;
    }

    private static class Node {

        int        value;
        Node       parent;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {

            return Objects.hash(value);
        }

    }

    public static void main(String[] args) {
        Assert.assertEquals(3,
                quickestWayUp(new int[][]{{32, 62}, {42, 68}, {12, 98}},
                        new int[][]{{95, 13}, {97, 25}, {93, 37}, {79, 27},
                                {75, 19}, {49, 47}, {67, 17}}));
        Assert.assertEquals(5,
                quickestWayUp(new int[][]{{8, 52}, {6, 80}, {26, 42}, {2, 72}},
                        new int[][]{{51, 19}, {39, 11}, {37, 29}, {81, 3},
                                {59, 5}, {79, 23}, {53, 7}, {43, 33}, {77, 21}
                        }));
        Assert.assertEquals(3,
                quickestWayUp(new int[][]{{3, 54}, {37, 100}},
                        new int[][]{{56, 33}
                        }));

    }
}
