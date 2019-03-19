package practice;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CoinChange {

    static class Pair {
        long n;
        int i;

        public Pair(long n, int i) {
            this.n = n;
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return n == pair.n &&
                    i == pair.i;
        }

        @Override
        public int hashCode() {

            return Objects.hash(n, i);
        }
    }


    static long getWays(long n, long[] c) {
        List<Long> availableVals =
                Arrays.stream(c).boxed().sorted().collect(Collectors.toList());
        Map<Pair, Long> resultCache = new HashMap<>();
        return getWays(n, availableVals, 0, resultCache);
    }

    static long getWays(
            long n,
            List<Long> availableValues,
            // avoid creating sublist multiple times
            int indexSuffix,
            Map<Pair, Long> resultCache
    ) {
        Pair pair = new Pair(n, indexSuffix);
        if (!resultCache.containsKey(pair)) {
            if (n < availableValues.get(indexSuffix)) { resultCache.put(pair, 0L);}
            else {
                long result = 0;
                for (int i = indexSuffix; i < availableValues.size(); i++) {
                    long newN = n - availableValues.get(i);
                    if (newN == 0) result += 1;
                    else if (newN < 0) break;
                    else {
                        result += getWays(newN, availableValues, i, resultCache);
                    }
                }
                resultCache.put(pair, result);
            }

        }
        return resultCache.get(pair);
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        long[] c = new long[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cItems[i]);
            c[i] = cItem;
        }

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = getWays(n, c);
        System.out.println(ways);

//        bufferedWriter.close();

        scanner.close();
    }
}
