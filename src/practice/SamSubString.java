package practice;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class SamSubString {
    private static final Map<Integer, Long> N_MAP = initNMap();

    private static final int MODULO = (int) 1e9 + 7;

    private static Map<Integer, Long> initNMap() {
        Map<Integer, Long> nMap = new HashMap<>();
        long walk = 1;
        nMap.put(1, walk);
        for (int i = 2; i <= 2 * 100000; i++) {
            walk = Math.floorMod((walk * 10 + 1), MODULO);
            nMap.put(i, walk);
        }
        return nMap;
    }

    static long substrings(String n) {
        long sum = 0;
        for (int i = 0; i < n.length(); i++) {
            int cur = Character.getNumericValue(n.charAt(i));
            sum += Math.floorMod(cur * (i + 1) * N_MAP.get(n.length() - i), MODULO);
        }
        return Math.floorMod(sum, MODULO);
    }

    public static void main(String[] args) {
        Assert.assertEquals(23, substrings("16"));
        Assert.assertEquals(164, substrings("123"));
        Assert.assertEquals(445677619, substrings("972698438521"));

    }
}
