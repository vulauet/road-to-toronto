package practice;

import org.junit.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ComponentsInAGraph {
    static int[] componentsInGraph(int[][] gb) {
        List<Integer> roots =
                IntStream.range(0, gb.length * 2 + 1)
                        .boxed()
                        .collect(Collectors.toList());

        for (int[] pair : gb) {
            int rootA = root(roots, pair[0]);
            int rootB = root(roots, pair[1]);
            setRoot(roots, rootA, rootB);
        }

        Map<Integer, Integer> connectedComponentSize = new HashMap<>();
        roots.forEach(
                i -> connectedComponentSize.put(i,
                        connectedComponentSize.getOrDefault(i, 0) + 1)
        );

        return new int[]{
                connectedComponentSize.values().stream().filter(i -> i > 1).min
                        (Integer::compareTo).get(),
                Collections.max(connectedComponentSize.values())
        };
    }

    private static void setRoot(List<Integer> roots, int root, int node) {
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i) == node) roots.set(i, root);
        }
    }

    private static int root(List<Integer> roots, int i) {
        return roots.get(i);
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{2, 4}, componentsInGraph(new int[][]{
                {1, 6},
                {2, 7},
                {3, 8},
                {4, 9},
                {2, 6}
        }));
        Assert.assertArrayEquals(new int[]{11, 11}, componentsInGraph(new int[][]{
                {1, 17},
                {5, 13},
                {7, 12},
                {5, 17},
                {5, 12},
                {2, 17},
                {1, 18},
                {8, 13},
                {2, 15},
                {5, 20}
        }));

    }
}
