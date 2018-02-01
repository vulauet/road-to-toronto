package io_utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vula on 02/04/2017.
 */
public class CodeJamInput {
    private int numOfCase;
    private List<ICommonInput> data;

    public int getNumOfCase() {
        return numOfCase;
    }

    public ICommonInput getIthCase(int i) {
//        Assert.assertTrue(i > -1);
//        Assert.assertTrue(i < data.size());
        return data.get(i);
    }

    public CodeJamInput(ICommonInputParser icip) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        numOfCase = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        data = new ArrayList<>();
        for (int i = 1; i <= numOfCase; ++i) data.add(icip.parse(in));
//        Assert.assertTrue(numOfCase > 0);
//        Assert.assertEquals(numOfCase, data.size());
    }
}
