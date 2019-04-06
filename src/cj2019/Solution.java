package cj2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int b = in.nextInt();
            int f = in.nextInt();

            List<String> msgList = constructMsgList(n);
            List<String> rspList = new ArrayList<>();
            for (String msg : msgList) {
                System.out.println(msg);
                rspList.add(in.next());
            }

            List<Integer> brokenWorkers = solve(n, b, rspList);
            StringBuilder sb = new StringBuilder();
            for (Integer brokenWorker : brokenWorkers) sb.append(brokenWorker).append(" ");
            System.out.println("Case #" + i + ": " + sb.toString());

        }
    }

    private static int log2(int i) {
        return (int) (Math.log(i) / Math.log(2));
    }

    private static List<String> constructMsgList(int n) {
        List<String> msgList = new ArrayList<>();
        int splitPoint = (int) Math.pow(2, log2(n - 1));
        while (splitPoint >= 1) {
            int bit = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(bit);
                if (i % splitPoint == 0) {
                    bit = 1 - bit;
                }
            }
            msgList.add(sb.toString());
            splitPoint /= 2;
        }
        return msgList;
    }

    private static List<Integer> solve(int n, int b, List<String> responses) {
        List<Integer> results = new ArrayList<>();
        if (responses.size() == 5) {
            return solveLarge(n, responses, results);
        } else {
            return solveSmall(n, b, responses, results);
        }
    }

    private static List<Integer> solveSmall(
            int n,
            int b,
            List<String> responses,
            List<Integer> results
    ) {

        List<String> firstResp = Arrays.asList(responses.get(0)
                                                        .replaceAll("01", "0 1")
                                                        .replaceAll("10", "1 0")
                                                        .split(" "));

        int seqLength = 8;
        if (n % seqLength > 0 && firstResp.size() < n / seqLength + 1) {
            for (int i = n - n % seqLength; i < n; i++) {
                results.add(i);
            }
        }

        int walk = 0;
        for (int i = 0; i < firstResp.size(); i++) {
            int respLength = firstResp.get(i).length();
            if (respLength < seqLength) {
                List<String> subResponses = new ArrayList<>();
                for (int j = 1; j < responses.size(); j++) {
                    subResponses.add(responses.get(j).substring(walk, walk + respLength));
                    walk += respLength;
                }
                List<Integer> subResults = solve(seqLength, seqLength - respLength, subResponses);
                for (Integer subResult : subResults) results.add(i * seqLength + subResult);
            }
        }
        Collections.sort(results);
        return results;
    }

    private static List<Integer> solveLarge(int n, List<String> responses, List<Integer> results) {
        List<String> firstResp = Arrays.asList(responses.get(0)
                                                        .replaceAll("01", "0 1")
                                                        .replaceAll("10", "1 0")
                                                        .split(" "));

        if (n % 16 > 0)
            if (firstResp.size() < n / 16 + 1) {
                for (int i = n - n % 16; i < n; i++) results.add(i);
            }

        int walk = 0;
        for (int i = 0; i < firstResp.size(); i++) {
            int respLength = firstResp.get(i).length();
            if (respLength < 16) {
                List<String> subResponses = new ArrayList<>();
                for (int j = 1; j < responses.size() - 1; j++) {
                    subResponses.add(responses.get(j).substring(walk, walk + respLength));
                    walk += respLength;
                }
                List<Integer> subResults = solve(16, 16 - respLength, subResponses);
                for (Integer subResult : subResults) results.add(i * 16 + subResult);
            }


        }
        Collections.sort(results);
        return results;
    }
}
