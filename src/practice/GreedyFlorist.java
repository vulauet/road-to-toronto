package practice;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyFlorist {
    private static int getMinimumCost(int k, int[] c) {
        List<Integer> priceList = Arrays.stream(c)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        int sum = 0;
        for (int i = 0; i < priceList.size(); i++) {
            sum += priceList.get(i) * (1 + i / k);
        }
        return sum;
    }

    public static void main(String[] args) {
        Assert.assertEquals(13, getMinimumCost(3, new int[]{2, 5, 6}));
        Assert.assertEquals(15, getMinimumCost(2, new int[]{2, 5, 6}));
        Assert.assertEquals(29, getMinimumCost(3, new int[]{1, 3, 5, 7, 9}));
    }
}
