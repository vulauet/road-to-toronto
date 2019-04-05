package practice;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumLoss {
    static long minimumLoss(long[] price) {
//        return fastMinLoss(price);
        return simpleMinLoss(price);
    }

    private static long simpleMinLoss(long[] price) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < price.length - 1; i++) {
            for (int j = i + 1; j < price.length; j++) {
                int loss = (int) (price[i] - price[j]);
                if (loss < min && loss > 0) min = loss;
            }
        }
        return min;
    }

    private static long fastMinLoss(long[] price) {
        Map<Long, Integer> firstApp =
                IntStream.range(0, price.length).boxed().collect(Collectors.toMap(
                        i -> price[i],
                        i -> i,
                        (k1, k2) -> k1
                ));

        List<Long> sortedPrice = Arrays.stream(price).boxed().sorted().collect(Collectors.toList());
        return IntStream.range(1, price.length)
                        .filter(i -> firstApp.get(sortedPrice.get(i))
                                < firstApp.get(sortedPrice.get(i - 1)))
                        .mapToLong(i -> sortedPrice.get(i) - sortedPrice.get(
                                i - 1))
                        .min().orElse(0);
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, minimumLoss(new long[]{20, 15, 8, 2, 12}));
        Assert.assertEquals(2, minimumLoss(new long[]{5, 10, 3}));
        Assert.assertEquals(2, minimumLoss(new long[]{20, 7, 8, 2, 5}));
    }
}
