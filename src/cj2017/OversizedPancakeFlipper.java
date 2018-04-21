package cj2017;

import io_utils.*;

import java.util.*;

/**
 * Created by vula on 08/04/2017.
 */
public class OversizedPancakeFlipper {

    private static final int UPPER_BOUND = 1000000;

    public static void main(String[] args) {
        CodeJamInput input = new CodeJamInput(new OversizedPancakeFlipperParser());
        List<ICommonOutput> outputList = new ArrayList<>();
        for (int i = 0; i < input.getNumOfCase(); i++) {
            ICommonInput ithCase = input.getIthCase(i);
//            Assert.assertTrue(ithCase instanceof OversizedPancakeFlipperInput);
            OversizedPancakeFlipperInput opfi = (OversizedPancakeFlipperInput) ithCase;
            String cakes = opfi.getCakes();
            ICommonOutput output = new OversizedPancakeFlipperOutput(flipCake(trimCake(cakes), opfi.getFlipperSize()));
            outputList.add(output);
        }
        CodeJamOutput.writeOutput(outputList);
    }

    private static int flipCake(String cakes, int flipperSize) {
        if (cakes.length() == 0) return 0;
        if (cakes.length() < flipperSize) return -UPPER_BOUND;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flipperSize; i++) {
            if (cakes.charAt(i) == '-') sb.append('+');
            else sb.append('-');
        }
        sb.append(cakes.substring(flipperSize));
        String smallerCake = trimCake(sb.toString());
        return 1 + flipCake(smallerCake, flipperSize);
    }

    private static String trimCake(String cakes) {
        int begin = cakes.indexOf("-");
        if (begin == -1) return "";
        int end = cakes.lastIndexOf("-");
        return cakes.substring(begin, end + 1);
    }

    private static class OversizedPancakeFlipperParser implements ICommonInputParser {
        @Override
        public ICommonInput parse(Scanner scanner) {
            String cakes = scanner.next();
            int flipperSize = scanner.nextInt();
            return new OversizedPancakeFlipperInput(cakes, flipperSize);
        }
    }

    private static class OversizedPancakeFlipperInput implements ICommonInput {
        private String cakes;
        private int flipperSize;

        OversizedPancakeFlipperInput(String s, int flipperSize) {
            this.cakes = s;
            this.flipperSize = flipperSize;
        }

        String getCakes() {
            return cakes;
        }

        int getFlipperSize() {
            return flipperSize;
        }
    }

    private static class OversizedPancakeFlipperOutput implements ICommonOutput {
        private int step;

        OversizedPancakeFlipperOutput(int i) {
            step = i;
        }

        @Override
        public String toString() {
            if (step < 0) return "IMPOSSIBLE";
            else return String.valueOf(step);
        }
    }
}
