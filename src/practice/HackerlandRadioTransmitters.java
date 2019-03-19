package practice;

import org.junit.Assert;

import java.util.Arrays;

public class HackerlandRadioTransmitters {

    public static void main(String[] args) {
        Assert.assertEquals(2,
                hackerlandRadioTransmitters(new int[]{1, 2, 3, 4, 5}, 1));
        Assert.assertEquals(4,
                hackerlandRadioTransmitters(new int[]{9, 5, 4, 2, 6, 15, 12}, 2));
        Assert.assertEquals(3,
                hackerlandRadioTransmitters(new int[]{7, 2, 4, 6, 5, 9, 12, 11}, 2));
    }

    private static int hackerlandRadioTransmitters(int[] x, int k) {
        Arrays.sort(x);
        int transmitterCount = 0;
        int i = 0;
        while (i < x.length) {
            transmitterCount++;
            int loc = x[i] + k;
            while (i < x.length && x[i] <= loc) i++;
            loc = x[--i] + k;
            while (i < x.length && x[i] <= loc) i++;
        }
        return transmitterCount;
    }

}
