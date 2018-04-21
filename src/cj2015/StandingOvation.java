package cj2015;

import io_utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StandingOvation implements ISolver {
    private final int[] shynessAtLevel;

    public StandingOvation(StandingOvationInput inp) {
        this.shynessAtLevel = inp.getShynessAtLevel();
    }

    public static void main(String[] args) {
        CodeJamInput input = new CodeJamInput(new StandingOvationParser());
        List<ICommonOutput> outputList = new ArrayList<>();
        for (int i = 0; i < input.getNumOfCase(); i++) {
            ICommonInput ithCase = input.getIthCase(i);
            StandingOvationInput inp = (StandingOvationInput) ithCase;
            StandingOvation solver = new StandingOvation(inp);
            StandingOvationOutput output = solver.solve();
            outputList.add(output);
        }
        CodeJamOutput.writeOutput(outputList);
    }

    public StandingOvationOutput solve() {
        int actual = shynessAtLevel[0];
        int expected = shynessAtLevel[0];
        for (int i = 1; i < shynessAtLevel.length; i++) {
            actual += shynessAtLevel[i];
            expected = Math.max(expected, i) + shynessAtLevel[i];
        }
        return new StandingOvationOutput(expected - actual);
    }

    private static class StandingOvationParser implements ICommonInputParser {
        @Override
        public ICommonInput parse(Scanner scanner) {
            int sMax = scanner.nextInt();
            String shynessByLevel = scanner.next();
            int[] shynessAtLevel = new int[shynessByLevel.length()];
            for (int i = 0; i < shynessByLevel.length(); i++) {
                shynessAtLevel[i] = Character.getNumericValue(shynessByLevel.charAt(i));
            }
            return new StandingOvationInput(shynessAtLevel);
        }

    }

    private static class StandingOvationInput implements ICommonInput {
        int[] shynessAtLevel;

        public StandingOvationInput(int[] shynessAtLevel) {
            this.shynessAtLevel = shynessAtLevel;
        }

        public int[] getShynessAtLevel() {
            return shynessAtLevel;
        }
    }

    private static class StandingOvationOutput implements ICommonOutput {

        private int numFriend;

        public StandingOvationOutput(int numFriend) {
            this.numFriend = numFriend;
        }

        @Override
        public String toString() {
            return String.valueOf(numFriend);
        }
    }
}
