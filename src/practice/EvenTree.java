package practice;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenTree {
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        Map<Integer, Integer> descendantCount = countDescendant(t_from, t_to);
        return (int) (descendantCount.values().stream().filter(i -> i % 2 == 1).count() - 1);
    }

    private static Map<Integer, Integer> countDescendant(List<Integer> t_from, List<Integer> t_to) {
        Map<Integer, Integer> descendantCount = new HashMap<>();
        Map<Integer, Integer> nodeMap = new HashMap<>();
        for (int i = 0; i < t_to.size(); i++) {
            Integer from = t_from.get(i);
            Integer to = t_to.get(i);
            nodeMap.put(from, to);
            descendantCount.put(
                    to,
                    descendantCount.getOrDefault(to, 0)
                            + descendantCount.getOrDefault(from, 0) + 1);

            from = to;
            to = nodeMap.get(from);

            while (to != null) {
                descendantCount.put(to, descendantCount.getOrDefault(to, 0) + 1);
                from = to;
                to = nodeMap.get(from);
            }

        }
        return descendantCount;
    }

    public static void main(String[] args) {
        Assert.assertEquals(
                2,
                evenForest(10, 9,
                           Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10),
                           Arrays.asList(1, 1, 3, 2, 1, 2, 6, 8, 8)
                )
        );

        Assert.assertEquals(
                1,
                evenForest(4, 3,
                           Arrays.asList(2, 3, 4),
                           Arrays.asList(1, 1, 3)
                )
        );
    }
}
