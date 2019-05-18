package cj2019.round_1c;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SolutionTest {

    @Test
    public void solve() {
        Assert.assertEquals("P",
                RobotProgrammingStrategy.solveSmall(new ArrayList<>(Collections.singletonList("RS"))));
        Assert.assertEquals("IMPOSSIBLE",
                RobotProgrammingStrategy.solveSmall(new ArrayList<>(Arrays.asList("R", "P", "S"))));
        Assert.assertEquals("P",
                RobotProgrammingStrategy.solveSmall(
                        new ArrayList<>(
                                Arrays.asList("RS", "RS", "RS", "RS", "RS", "RS", "RS")
                        )));
    }
}