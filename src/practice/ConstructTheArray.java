package practice;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConstructTheArray {

    static class Pair {
        int k;
        int n;

        public Pair(int k, int n) {
            this.k = k;
            this.n = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return k == pair.k &&
                    n == pair.n;
        }

        @Override
        public int hashCode() {

            return Objects.hash(k, n);
        }
    }

    private static final long MODULO = (long) 1e9 + 7;
    private static final Map<Pair, Long> CACHE = new HashMap<>();

    static long countArray(int n, int k, int x) {
        // Return the number of ways to fill in the array.
        Map<Pair, Long> countArrCache = new HashMap<>();
        return countArray(n, k, x, countArrCache);
    }

    private static long countArray(int n, int k, int x, Map<Pair, Long> countArrCache) {
        if (n == 3) return x == 1 ? k - 1 : k - 2;
        Pair pair = new Pair(k, n);
        if (!countArrCache.containsKey(pair)) {
            long countArrAny = countArray(n - 1, k);
            long countArrX = countArray(n - 1, k, x, countArrCache);
            countArrCache.put(pair, Math.floorMod ((countArrAny - countArrX), MODULO));
        }
        return countArrCache.get(pair);
    }

    private static long countArray(int n, int k) {
        if (n <= 3) return Math.floorMod((long) Math.pow(k - 1, n - 1), MODULO);
        Pair pair = new Pair(k, n);
        if (!CACHE.containsKey(pair)) {
            long countAny = Math.floorMod(countArray(n - 1, k) * (k - 1), MODULO);
            CACHE.put(pair, countAny);
        }
        return CACHE.get(pair);
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, countArray(4, 3, 2));
        Assert.assertEquals(0, countArray(5, 2, 2));
//        Assert.assertEquals(236568308, countArray(761, 99, 1));
        Assert.assertEquals(804842436, countArray(942, 77, 13));
    }
}
