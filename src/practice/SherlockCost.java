package practice;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class SherlockCost {
    static int cost(int[] B) {
        List<Integer> A1 = new ArrayList<>();
        List<Integer> Ab = new ArrayList<>();
        List<Integer> S = new ArrayList<>();

        A1.add(B[0] - 1);
        Ab.add(B[1] - 1);
        S.add(Math.max(A1.get(0), Ab.get(0)));
        for (int i = 1; i < B.length - 1; i++) {
            A1.add(Ab.get(i - 1) + B[i] - 1);
            Ab.add(Math.max(A1.get(i - 1) + B[i + 1] - 1,
                    Ab.get(i - 1) + Math.abs(B[i] - B[i + 1])));
            S.add(Math.max(A1.get(i), Ab.get(i)));

        }
        return S.get(S.size() - 1);
    }


    public static void main(String[] args) {
        Assert.assertEquals(396, cost(new int[]{100, 2, 100, 2, 100}));
        Assert.assertEquals(50, cost(new int[]{3, 15, 4, 12, 10}));
        Assert.assertEquals(12, cost(new int[]{4, 7, 9}));
    }
}
