package cj2018.round_1c;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solve1() {
        Assert.assertEquals(1, new Solution(Arrays.asList(9, 1)).solve());
    }

    @Test
    public void solve2() {
        Assert.assertEquals(3, new Solution(Arrays.asList(8, 4, 100)).solve());
    }

    @Test
    public void solve3() {
        Assert.assertEquals(8, new Solution(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 100)).solve());
    }

    @Test
    public void solve4() {
        Assert.assertEquals(8, new Solution(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 100)).solve());
    }


    @Test
    public void solve5() {
        Assert.assertEquals(8, new Solution(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 100)).solve());
    }

    @Test
    public void solve7() {
        Assert.assertEquals(7, new Solution(Arrays.asList(1, 1, 2, 2, 1, 1, 1, 1)).solve());
    }



}