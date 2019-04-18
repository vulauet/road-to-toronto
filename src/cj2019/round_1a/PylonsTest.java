package cj2019.round_1a;

import org.junit.Test;

public class PylonsTest {

    @Test
    public void solveSmall() {
//        Assert.assertEquals("IMPOSSIBLE", PylonsNode.solve(2, 2));
//        Assert.assertEquals("POSSIBLE", PylonsNode.solve(2, 5));
//        PylonsNode.solve(3, 9);
        for (int i = 2; i <= 20; i++) {
            for (int j = 2; j <= 20; j++) {
                long startTime = System.currentTimeMillis();
                System.out.println(i + "," + j);
                System.out.println(PylonsNode.solve(i, j));
                System.out.println(
                        "Elapsed time " + (System.currentTimeMillis() - startTime) / 1000 + "s");
                System.out.println();
            }
        }
    }
}