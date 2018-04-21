package cj2018.solved;

import org.junit.Assert;

public class SavingUniverseTest {

    @org.junit.Test
    public void test1() {
        Assert.assertEquals(1, new SavingUniverse(1, "CS").solve());
    }

    @org.junit.Test
    public void test2() {
        Assert.assertEquals(0, new SavingUniverse(2, "CS").solve());
    }

    @org.junit.Test
    public void test3() {
        Assert.assertEquals(-1, new SavingUniverse(1, "SS").solve());
    }

    @org.junit.Test
    public void test4() {
        Assert.assertEquals(2, new SavingUniverse(6, "SCCSSC").solve());
    }

    @org.junit.Test
    public void test5() {
        Assert.assertEquals(0, new SavingUniverse(2, "CC").solve());
    }

    @org.junit.Test
    public void test6() {
        Assert.assertEquals(5, new SavingUniverse(3, "CSCSS").solve());
    }

    @org.junit.Test(timeout = 20000)
    public void test7() {
        Assert.assertEquals(-1, new SavingUniverse(1, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCSS").solve());
    }


}