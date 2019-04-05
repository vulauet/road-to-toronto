package practice.mock_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SenateEvacuation {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            PriorityQueue<Party> pq =
                    new PriorityQueue<>(Comparator.comparingInt(Party::getMemCount).reversed());
            for (int j = 0; j < n; j++)
                pq.add(new Party(j, in.nextInt()));
            solve(pq, i);
        }
    }

    private static void solve(PriorityQueue<Party> parties, int i) {
        List<String> evacuateList = evacuateList(parties);
        System.out.print("Case #" + i + ": ");
        for (String evacuate : evacuateList) {
            System.out.print(evacuate + " ");
        }
        System.out.println();
    }

    private static List<String> evacuateList(PriorityQueue<Party> parties) {
        if (parties.isEmpty()) return new ArrayList<>();

        Party p1 = parties.poll();
        Party p2 = parties.poll();
        String firstPick = String.valueOf(p1.initial);

        if (parties.isEmpty()) {
            String evacuate = firstPick + p2.initial;
            return IntStream.range(0, p1.memCount)
                            .mapToObj(i -> evacuate)
                            .collect(Collectors.toList());
        } else {
            List<String> curRescue = new ArrayList<>();
            if (p1.memCount - p2.memCount >= 2) {
                p1.evacuate(1);
                curRescue.add(firstPick + p1.initial);
            } else if (parties.size() == 1 && p1.memCount == 1) {
                curRescue.add(firstPick);
            } else {
                p2.evacuate(1);
                curRescue.add(firstPick + p2.initial);
            }
            p1.evacuate(1);
            if (p1.memCount > 0) parties.add(p1);
            if (p2.memCount > 0) parties.add(p2);
            curRescue.addAll(evacuateList(parties));
            return curRescue;
        }
    }

    private static class Party {
        private char initial;
        private int memCount;

        public Party(int i, int memCount) {
            this.initial = (char) ('A' + i);
            this.memCount = memCount;
        }

        public int getMemCount() {
            return memCount;
        }

        public void evacuate(int evacuateCount) {
            this.memCount -= evacuateCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Party party = (Party) o;
            return initial == party.initial;
        }

        @Override
        public int hashCode() {
            return Objects.hash(initial);
        }
    }
}
