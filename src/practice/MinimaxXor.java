package practice;

import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class MinimaxXor {

    static int anotherMinimaxProblem(int[] a) {
        Map<Integer, Integer> intToBinLength =
                Arrays.stream(a).distinct().boxed().collect(
                        Collectors.toMap(
                                i -> i,
                                i -> Integer.toBinaryString(i).length()
                        )
                );
        if (intToBinLength.size() == 1) return 0;
        int maxLength = Collections.max(intToBinLength.values());
        int minLength = Collections.min(intToBinLength.values());
        if (minLength == maxLength) {
            int pow = (int) Math.pow(2, maxLength - 1);
            for (int i = 0; i < a.length; i++)
                a[i] = a[i] - pow;
            return anotherMinimaxProblem(a);
        } else {
            List<Integer> mostSignificants =
                    Arrays.stream(a)
                          .filter(i -> intToBinLength.get(i) == maxLength)
                          .boxed()
                          .collect(Collectors.toList());
            List<Integer> leastSignificants =
                    Arrays.stream(a)
                          .filter(i -> intToBinLength.get(i) != maxLength)
                          .boxed()
                          .collect(Collectors.toList());
            int min = Integer.MAX_VALUE;
            for (Integer mostSignificant : mostSignificants) {
                for (Integer leastSignificant : leastSignificants) {
                    int xor = mostSignificant ^ leastSignificant;
                    if (xor < min) {
                        min = xor;
                    }
                }
            }
            return min;
        }
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, anotherMinimaxProblem(new int[]{1, 2, 3}));
        Assert.assertEquals(5, anotherMinimaxProblem(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(4, anotherMinimaxProblem(new int[]{12, 8, 9, 11, 14}));
        Assert.assertEquals(8, anotherMinimaxProblem(new int[]{12, 0, 4, 3, 1, 1, 12, 3, 11, 11}));
        Assert.assertEquals(0, anotherMinimaxProblem(new int[]{0, 0, 0, 0, 0, 0, 0, 0}));
    }
}
