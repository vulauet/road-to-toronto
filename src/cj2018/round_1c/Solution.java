package cj2018.round_1c;

import java.util.*;
import java.io.*;


public class Solution {
    private List<Integer> weightList;
    private Map<IndexWeightPair, AntStack> cache;

    public Solution(List<Integer> weightList) {
        this.weightList = weightList;
        this.cache = new HashMap<>();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            List<Integer> weightList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                weightList.add(in.nextInt());
            }
            System.out.println("Case #" + i + ": " + new Solution(weightList).solve());
        }
    }

    public int solve() {
        if (weightList.size() <= 100) {
            return solveSmall();
        }
        return 1;
    }

    private int solveSmall() {
        AntStack stack = findStack(weightList);
        return stack.size;
    }

    private AntStack findStack(List<Integer> weightList) {
        return findStack(weightList, weightList.size() - 1, Integer.MAX_VALUE);
    }

    private AntStack findStack(List<Integer> weightList, int lastIndex, int weightLimit) {

        if (weightList.size() == 1) return new AntStack(weightList.get(0), 1);
        if (weightLimit == 0) return new AntStack(0, 0);
        if (lastIndex == 0) {
            Integer weight = weightList.get(lastIndex);
            if (weight <= weightLimit) return new AntStack(weight, 1);
            return new AntStack(0, 0);
        }

        Integer curWeight = weightList.get(lastIndex);
        int prevIndex = lastIndex - 1;
        int nextWeightLimit = Math.min(weightLimit - curWeight, curWeight * 6);
        IndexWeightPair iwp = new IndexWeightPair(prevIndex, nextWeightLimit);
        if (!cache.containsKey(iwp)) {
            AntStack stack = findStack(weightList, prevIndex, nextWeightLimit);
            stack.size += 1;
            stack.totalWeight += curWeight;
            cache.put(iwp, stack);
        }
        AntStack containCur = cache.get(iwp);

        IndexWeightPair skiwp = new IndexWeightPair(prevIndex, weightLimit);
        if (!cache.containsKey(skiwp)) {
            cache.put(skiwp, findStack(weightList, prevIndex, weightLimit));
        }
        AntStack skipCur = cache.get(skiwp);
        return containCur.size > skipCur.size ? containCur : skipCur;
    }

    private class AntStack {
        int totalWeight;
        int size;

        public AntStack(int totalWeight, int size) {
            this.totalWeight = totalWeight;
            this.size = size;
        }
    }

    private class IndexWeightPair {
        int index;
        int weightLimit;

        public IndexWeightPair(int index, int weightLimit) {
            this.index = index;
            this.weightLimit = weightLimit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IndexWeightPair that = (IndexWeightPair) o;
            return index == that.index &&
                    weightLimit == that.weightLimit;
        }

        @Override
        public int hashCode() {

            return Objects.hash(index, weightLimit);
        }
    }
}
