package cj2018.round_1a;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WaffleChoppersTest {

    @Test
    public void testAnswer1() {
        List<String> waffle1 = Arrays.asList(".@@..@", ".....@", "@.@.@@");
        Assert.assertEquals("POSSIBLE", new WaffleChoppers(1, 1, listToMatrix(waffle1)).answer());
    }

    @Test
    public void testAnswer2() {
        List<String> waffle2 = Arrays.asList("@@@", "@.@", "@.@", "@@@");
        Assert.assertEquals("IMPOSSIBLE", new WaffleChoppers(1, 1, listToMatrix(waffle2)).answer());
    }

    @Test
    public void testAnswer3() {
        List<String> waffle3 = Arrays.asList(".....", ".....", ".....", ".....");
        Assert.assertEquals("POSSIBLE", new WaffleChoppers(1, 1, listToMatrix(waffle3)).answer());
    }

    @Test
    public void testAnswer4() {
        List<String> waffle4 = Arrays.asList("..@@", "..@@", "@@..", "@@..");
        Assert.assertEquals("IMPOSSIBLE", new WaffleChoppers(1, 1, listToMatrix(waffle4)).answer());
    }

    @Test
    public void testAnswer5() {
        List<String> waffle5 = Arrays.asList("@.@@", "@@.@", "@.@@");
        Assert.assertEquals("POSSIBLE", new WaffleChoppers(2, 2, listToMatrix(waffle5)).answer());
    }

    @Test
    public void testAnswer6() {
        List<String> waffle6 = Arrays.asList(".@.@", "@.@.", ".@.@");
        Assert.assertEquals("IMPOSSIBLE", new WaffleChoppers(1, 2, listToMatrix(waffle6)).answer());
    }

    private char[][] listToMatrix(List<String> waffle) {
        int R = waffle.size();
        int C = waffle.get(0).length();
        char[][] matrix = new char[R][C];
        for (int j = 0; j < R; j++) {
            String row = waffle.get(j);
            for (int k = 0; k < C; k++) {
                matrix[j][k] = row.charAt(k);
            }
        }
        return matrix;
    }
}