package practice;

import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JourneyToTheMoon {
    static long journeyToMoon(int n, int[][] astronaut) {
        List<Integer> roots =
                IntStream.range(0, n).boxed().collect(Collectors.toList());

        for (int[] pair : astronaut) {
            int rootA = root(roots, pair[0]);
            int rootB = root(roots, pair[1]);
            setRoot(roots, rootA, rootB);
        }

        Map<Integer, Integer> countryMemberCount = new HashMap<>();
        roots.forEach(
                i -> countryMemberCount.put(i, countryMemberCount.getOrDefault(i, 0) + 1)
        );

        long totalPairCount = countPair(n);
        long sameCountryPair =
                countryMemberCount.values().stream()
                        .mapToLong(JourneyToTheMoon::countPair)
                        .sum();
        return totalPairCount - sameCountryPair;
    }

    private static void setRoot(List<Integer> roots, int root, int node) {
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i) == node) roots.set(i, root);
        }
    }

    private static int root(List<Integer> roots, int i) {
        return roots.get(i);
    }

    private static long countPair(int n) {
        return (long) n * (n - 1) / 2;
    }

    public static void main(String[] args) {
        Assert.assertEquals(6, journeyToMoon(5, new int[][]{{0, 1}, {2, 3}, {0, 4}}));
        Assert.assertEquals(5, journeyToMoon(4, new int[][]{{0, 2}}));
    }
}
