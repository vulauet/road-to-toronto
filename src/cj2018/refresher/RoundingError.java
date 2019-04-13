package cj2018.refresher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class RoundingError {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int l = in.nextInt();
            List<Integer> count = new ArrayList<>();
            for (int j = 0; j < l; j++) count.add(in.nextInt());
            System.out.println("Case #" + i + ": " + solve(n, l, count));
        }
    }

    private static int solve(int n, int l, List<Integer> count) {

        int curMaxCount = count.stream().mapToInt(i -> i).max().getAsInt();
        List<Integer> roundUp = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            double truePercentage = i * 100.0 / n;
            int roundedPercentage = (int) Math.round(truePercentage);
            if (truePercentage - roundedPercentage < 0) {
                roundUp.add(i);
                if (i >= curMaxCount) break;
            }
        }

        if (roundUp.isEmpty()) return 100;
        PriorityQueue<LangVote> toNextRoundUp
                = new PriorityQueue<>(Comparator.comparingInt(LangVote::distToRoundUp));
        for (int i = 0; i < count.size(); i++)
            toNextRoundUp.add(new LangVote(count.get(i), countToRoundUp(roundUp, count.get(i))));
        for (int i = 0; i < n - l; i++) toNextRoundUp.add(new LangVote(0, roundUp.get(0)));

        int voteLeft = n - l;
        int sum = 0;
        while (voteLeft > 0) {
            LangVote langVote = toNextRoundUp.poll();
            if (voteLeft >= langVote.distToRoundUp) {
                sum += (int) Math.round(langVote.getVoteCount() * 100.0 / n);
                if (langVote.curCount > 0) count.remove(count.indexOf(langVote.curCount));
                voteLeft -= langVote.distToRoundUp;
            } else {
                sum += (int) Math.round(voteLeft * 100.0 / n);
                voteLeft = 0;
            }
        }

        for (int i = 0; i < count.size(); i++) {
            sum += (int) Math.round(i * 100.0 / n);
        }
        return sum;
    }

    private static int countToRoundUp(List<Integer> roundUp, int curCount) {
        for (int i = 0; i < roundUp.size(); i++) {
            int diff = roundUp.get(i) - curCount;
            if (diff > 0) return diff;
        }
        return Integer.MAX_VALUE;
    }

    private static class LangVote {

        int curCount;
        int distToRoundUp;

        public LangVote(int curCount, int distToRoundUp) {
            this.curCount = curCount;
            this.distToRoundUp = distToRoundUp;
        }

        public int getCurCount() {
            return curCount;
        }

        public int distToRoundUp() {
            return distToRoundUp;
        }

        public int getVoteCount() {
            return curCount + distToRoundUp;
        }
    }
}
