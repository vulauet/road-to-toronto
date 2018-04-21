package io_utils;

import java.util.List;

/**
 * Created by vula on 02/04/2017.
 */
public class CodeJamOutput {
    public static void writeOutput(List<ICommonOutput> outputList) {
        for (int i = 0; i < outputList.size(); i++)
            System.out.println("Case #" + String.valueOf(i + 1) + ": " + outputList.get(i).toString());
    }
}
