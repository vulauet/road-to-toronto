package cj2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


//            List<Integer> brokenWorkers = solve(n, b, rspList);
            List<Integer> brokenWorkers = solveXor(n, b, msgList, rspList);
            StringBuilder sb = new StringBuilder();
            for (Integer brokenWorker : brokenWorkers) sb.append(brokenWorker).append(" ");
            System.out.println("Case #" + i + ": " + sb.toString());

        }
    }

    private static List<Integer> solveXor(
            int n,
            int b,
            List<String> msgList,
            List<String> rspList
    ) {
        Map<Integer, Integer> validIndexMap = new HashMap<>();
        for (int i = 0; i < n - b; i++) {
            int k = 0;
            for (int j = 0; j < msgList.size(); j++) {
                if ((rspList.get(j).charAt(i) ^ msgList.get(j).charAt(k)) != 0) {
                    k++;
                    break;
                }
            }
            validIndexMap.put(i, k);
        }
        Set<Integer> validIndexSet = new HashSet<>(validIndexMap.values());
        return IntStream.range(0, n)
                        .filter(i -> !validIndexSet.contains(i))
                        .boxed()
                        .collect(Collectors.toList());
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

        int walk = 0;
        int stepSize = 16;
        for (int i = 0; i < firstResp.size(); i++) {
            int respLength = firstResp.get(i).length();
            if (respLength < stepSize) {
                List<String> subResponses = new ArrayList<>();
                for (int j = 1; j < responses.size(); j++) {
                    subResponses.add(responses.get(j).substring(walk, walk + respLength));
                    walk += respLength;
                }

                int newN = i < firstResp.size() - 1 ? stepSize : Math.min(n % 16, stepSize);
                if (newN - respLength > 0) {
                    List<Integer> subResults = solve(newN, newN - respLength, subResponses);
                    for (Integer subResult : subResults) results.add(i * stepSize + subResult);
                }
            }

        }

        if (n % stepSize > 0 && firstResp.size() < n / stepSize + 1) {
            for (int i = n - n % stepSize; i < n; i++) {
                results.add(i);
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

        int walk = 0;
        int stepSize = 16;
        for (int i = 0; i < firstResp.size(); i++) {
            int respLength = firstResp.get(i).length();
            if (respLength < stepSize) {
                List<String> subResponses = new ArrayList<>();
                for (int j = 1; j < responses.size(); j++) {
                    subResponses.add(responses.get(j).substring(walk, walk + respLength));
                    walk += respLength;
                }

                int newN = i < firstResp.size() - 1 ? stepSize : Math.min(n % 16, stepSize);
                if (newN - respLength > 0) {
                    List<Integer> subResults = solve(newN, newN - respLength, subResponses);
                    for (Integer subResult : subResults) results.add(i * stepSize + subResult);
                }
            }

        }

        if (n % stepSize > 0 && firstResp.size() < n / stepSize + 1) {
            for (int i = n - n % stepSize; i < n; i++) {
                results.add(i);
            }
        }

        Collections.sort(results);
        return results;
    }
}
