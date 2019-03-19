package practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoadsAndLibs {

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib < c_road) return ((long) c_lib) * n;
        List<Integer> roots =
                IntStream.range(0, n).boxed().collect(Collectors.toList());
        Map<Integer, Set<Integer>> rootToNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rootToNodes.put(i, new HashSet<>(Collections.singleton(i)));
        }
        for (int[] conn : cities) {
            int oldRoot = roots.get(conn[1] - 1);
            int newRoot = roots.get(conn[0] - 1);
            if (oldRoot != newRoot)
                for (Integer i : rootToNodes.get(oldRoot)) {
                    roots.set(i, newRoot);
                    rootToNodes.get(newRoot).add(i);
                }
        }
        Set<Integer> distinctRoot = new HashSet<>(roots);
        return (long) distinctRoot.size() * c_lib +
                (long) (n - distinctRoot.size()) * c_road;
    }

    public static void main(String[] args) {
//        Assert.assertEquals(4, roadsAndLibraries(3, 2, 1, new int[][]{
//                {1, 2},
//                {3, 1},
//                {2, 3},
//        }));
//        Assert.assertEquals(12, roadsAndLibraries(6, 2, 5, new int[][]{
//                {1, 3},
//                {3, 4},
//                {2, 4},
//                {1, 2},
//                {2, 3},
//                {5, 6},
//        }));

        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            System.out.println(result);
        }
    }
}
