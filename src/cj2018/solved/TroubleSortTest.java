package cj2018.solved;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TroubleSortTest {

    @Test(timeout = 10000)
    public void test1() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(5, 8, 3));
        ArrayList<Integer> oddList = new ArrayList<>(Arrays.asList(6, 4));
        Assert.assertEquals("OK", new TroubleSort(evenList, oddList).answer());
    }

    @Test(timeout = 10000)
    public void test2() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(8, 7));
        ArrayList<Integer> oddList = new ArrayList<>(Collections.singletonList(9));
        Assert.assertEquals("1", new TroubleSort(evenList, oddList).answer());
    }

    @Test(timeout = 10000)
    public void test3() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(5, 6, 3));
        ArrayList<Integer> oddList = new ArrayList<>(Arrays.asList(6, 4));
        Assert.assertEquals("OK", new TroubleSort(evenList, oddList).answer());
    }

    @Test(timeout = 10000)
    public void test4() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(6, 10, 7));
        ArrayList<Integer> oddList = new ArrayList<>(Arrays.asList(5, 9, 8));
        Assert.assertEquals("0", new TroubleSort(evenList, oddList).answer());
    }

    @Test(timeout = 10000)
    public void test5() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(5, 10, 7));
        ArrayList<Integer> oddList = new ArrayList<>(Arrays.asList(6, 9, 8));
        Assert.assertEquals("4", new TroubleSort(evenList, oddList).answer());
    }

    @Test(timeout = 10000)
    public void test6() {
        ArrayList<Integer> evenList = new ArrayList<>(Arrays.asList(2, 6, 7));
        ArrayList<Integer> oddList = new ArrayList<>(Arrays.asList(4, 5, 8));
        Assert.assertEquals("2", new TroubleSort(evenList, oddList).answer());
    }




}