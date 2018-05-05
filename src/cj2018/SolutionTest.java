package cj2018;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void testRoundingError1() {
        Assert.assertEquals(100, new Solution(3, 2, new ArrayList<>(Arrays.asList(1, 1))).answer());
    }

    @Test
    public void testRoundingError2() {
        Assert.assertEquals(100, new Solution(10, 3, new ArrayList<>(Arrays.asList(1, 3, 2))).answer());
    }

    @Test
    public void testRoundingError3() {
        Assert.assertEquals(101, new Solution(6, 2, new ArrayList<>(Arrays.asList(3, 1))).answer());
    }

    @Test
    public void testRoundingError4() {
        Assert.assertEquals(99, new Solution(9, 8, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1))).answer());
    }

    @Test
    public void testRoundingError5() {
        Assert.assertEquals(100, new Solution(25, 1, new ArrayList<>(Arrays.asList(1))).answer());
    }


}