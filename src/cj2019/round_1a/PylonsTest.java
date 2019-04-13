package cj2019.round_1a;

import org.junit.Assert;
import org.junit.Test;

public class PylonsTest {

    @Test
    public void solveSmall() {
        Assert.assertEquals("IMPOSSIBLE", Pylons.solveSmall(2, 2));
        Assert.assertEquals("POSSIBLE", Pylons.solveSmall(2, 5));
        for (int i = 2; i <= 5; i++) {
            for (int j = 2; j <= 5; j++) {
                long startTime = System.currentTimeMillis();
                System.out.println(i + "," + j);
                System.out.println(Pylons.solveSmall(i, j));
                System.out.println(
                        "Elapsed time " + (System.currentTimeMillis() - startTime) / 1000 + "s");
                System.out.println();
            }
        }
    }
}