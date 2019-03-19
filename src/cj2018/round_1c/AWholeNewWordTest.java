package cj2018.round_1c;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AWholeNewWordTest {

    @Test
    public void solve1() {
        List<String> inputs = Arrays.asList("A", "B", "C", "D");
        Assert.assertEquals("-", new AWholeNewWord(inputs, 4, 1).solve());

    }

    @Test
    public void solve2() {
        List<String> inputs = Arrays.asList("WW", "AA", "SS", "DD");
        Assert.assertEquals("WA", new AWholeNewWord(inputs, 4, 2).solve());

    }

    @Test
    public void solve3() {
        List<String> inputs = Arrays.asList("AA", "AB", "BA", "BB");
        Assert.assertEquals("-", new AWholeNewWord(inputs, 4, 2).solve());

    }

    @Test
    public void solve4() {
        List<String> inputs = Arrays.asList("CAKE", "TORN", "SHOW");
        Assert.assertEquals("CORN", new AWholeNewWord(inputs, 3, 4).solve());

    }

    @Test
    public void solve5() {
        List<String> inputs = Arrays.asList("HELPIAM", "TRAPPED", "INSIDEA", "CODEJAM", "FACTORY");
        Assert.assertEquals("HOLIDAY", new AWholeNewWord(inputs, 5, 7).solve());

    }


}