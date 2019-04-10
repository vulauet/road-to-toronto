package cj2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DatBae {
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

            List<Integer> brokenWorkers = solve(n, b, msgList, rspList);
            StringBuilder sb = new StringBuilder();
            for (Integer brokenWorker : brokenWorkers) sb.append(brokenWorker).append(" ");
            System.out.println(sb.substring(0, sb.length() - 1));
            in.nextInt();
        }
    }

    private static List<Integer> solve(
            int n,
            int b,
            List<String> msgList,
            List<String> rspList
    ) {
        List<Integer> results = new ArrayList<>();
        List<String> msgSeq = verticalAccumulate(n, msgList);
        List<String> rspSeq = verticalAccumulate(n - b, rspList);
        int i = 0;
        int j = 0;
        while (j < rspSeq.size()) {
            while (!msgSeq.get(i).equals(rspSeq.get(j))) {
                results.add(i);
                i++;
            }
            i++;
            j++;
        }
        for (int k = i; k < msgSeq.size(); k++) {
            results.add(k);
        }
        return results;
    }

    private static List<String> verticalAccumulate(int n, List<String> horizontalList) {
        List<String> msgSeq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : horizontalList) {
                sb.append(s.charAt(i));
            }
            msgSeq.add(sb.toString());
        }
        return msgSeq;
    }

    private static int log2(int i) {
        return (int) (Math.log(i) / Math.log(2));
    }

    private static List<String> constructMsgList(int n) {
        List<String> msgList = new ArrayList<>();
        int splitPoint = Math.min(16, (int) Math.pow(2, log2(n - 1)));
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
}
