package practice;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class StringReduction {
    static Map<String, String> REDUCE_MAP = new HashMap<>();

    static int stringReduction(String s) {
        return reduce(s).length();
    }

    private static String reduce(String s) {
        if (s.length() <= 2) {
            switch (s) {
                case "ab":
                case "ba":
                    return "c";
                case "bc":
                case "cb":
                    return "a";
                case "ac":
                case "ca":
                    return "b";
                default:
                    return s;
            }
        }

        if (!REDUCE_MAP.containsKey(s)) {
            String result = s;
            for (int i = 1; i < s.length(); i++) {
                String reducedS =
                        reduce(s.substring(0, i)) + reduce(s.substring(i, s.length()));
                if (!reducedS.equals(s)) reducedS = reduce(reducedS);
                if (reducedS.length() < result.length()) result = reducedS;
            }
            REDUCE_MAP.put(s, result);
        }
        return REDUCE_MAP.get(s);
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, stringReduction("cab"));
        Assert.assertEquals(1, stringReduction("bcab"));
        Assert.assertEquals(5, stringReduction("ccccc"));
    }
}
