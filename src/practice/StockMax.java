package practice;

import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class StockMax {
    static long stockmax(int[] prices) {
        return proStockMax(prices);
//        return noobStockMax(prices);
    }

    private static long proStockMax(int[] prices) {
        long profit = 0L;
        int curMax = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[i] > curMax)
                curMax = prices[i];
            profit += curMax - prices[i];
        }
        return profit;
    }

    private static long noobStockMax(int[] prices) {
        List<SellPoint> sellPoints = findSellPrices(prices);
        long sum = 0;
        for (SellPoint sellPoint : sellPoints) {
            int walk = sellPoint.index - 1;
            while (walk >= 0 && prices[walk] < sellPoint.price) {
                sum += sellPoint.price - prices[walk];
                walk--;
            }
        }
        return sum;
    }

    private static List<SellPoint> findSellPrices(int[] prices) {
        List<Integer> priceList =
                Arrays.stream(prices).boxed().collect(Collectors.toList());
        List<SellPoint> results = new ArrayList<>();
        while (!priceList.isEmpty()) {
            int price = Collections.max(priceList);
            int index = priceList.indexOf(price);
            if (results.isEmpty()) results.add(new SellPoint(index, price));
            else results.add(new SellPoint(index + results.get(results.size() - 1)
                    .index + 1, price));
            priceList = priceList.subList(index + 1, priceList.size());
        }
        return results;
    }

    private static class SellPoint {
        int index;
        int price;

        public SellPoint(int index, int price) {
            this.index = index;
            this.price = price;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Assert.assertEquals(0, stockmax(new int[]{5, 3, 2}));
        Assert.assertEquals(197, stockmax(new int[]{1, 2, 100}));
        Assert.assertEquals(3, stockmax(new int[]{1, 3, 1, 2}));
        Assert.assertEquals(6, stockmax(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(6, stockmax(new int[]{1, 2, 3, 4, 3, 2}));
        Assert.assertEquals(7, stockmax(new int[]{1, 2, 3, 4, 3, 2, 3}));


        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] prices = new int[n];

            String[] pricesItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int pricesItem = Integer.parseInt(pricesItems[i]);
                prices[i] = pricesItem;
            }

            long result = stockmax(prices);
            System.out.println(result);
        }
        scanner.close();
    }
}
