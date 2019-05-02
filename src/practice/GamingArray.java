package practice;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class GamingArray {
    public static String gamingArray(List<Integer> arr) {
        // Write your code here
        int countTurn = 1;
        int curMax = arr.get(0);

        for (Integer num : arr) {
            if (num > curMax) {
                ++countTurn;
                curMax = num;
            }
        }
        return countTurn % 2 == 0 ? "ANDY" : "BOB";
    }

    public static void main(String[] args) {
        Assert.assertEquals("BOB", gamingArray(Arrays.asList(1, 3, 5, 7, 9)));
        Assert.assertEquals("ANDY", gamingArray(Arrays.asList(7, 4, 6, 5, 9)));
    }
}
