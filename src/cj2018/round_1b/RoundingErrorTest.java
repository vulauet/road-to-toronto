package cj2018.round_1b;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RoundingErrorTest {

    @Test
    public void testRoundingError1() {
        Assert.assertEquals(100, new RoundingError(3, 2, new ArrayList<>(Arrays.asList(1, 1))).answer());
    }

    @Test
    public void testRoundingError2() {
        Assert.assertEquals(100, new RoundingError(10, 3, new ArrayList<>(Arrays.asList(1, 3, 2))).answer());
    }

    @Test
    public void testRoundingError3() {
        Assert.assertEquals(101, new RoundingError(6, 2, new ArrayList<>(Arrays.asList(3, 1))).answer());
    }

    @Test
    public void testRoundingError4() {
        Assert.assertEquals(99, new RoundingError(9, 8, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1))).answer());
    }

    @Test
    public void testRoundingError5() {
        Assert.assertEquals(100, new RoundingError(25, 1, new ArrayList<>(Arrays.asList(1))).answer());
    }


}