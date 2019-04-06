package cj2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Cryptopangrams {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int l = in.nextInt();
            if (n <= 10000) {
                List<Integer> valueList = new ArrayList<>();
                for (int j = 0; j < l; j++) {
                    valueList.add(in.nextInt());
                }
                System.out.println("Case #" + i + ": " + decodeSmall(valueList));
            } else {
                List<BigInteger> valueList = new ArrayList<>();
                for (int j = 0; j < l; j++) {
                    valueList.add( in.nextBigInteger());
                }
                System.out.println("Case #" + i + ": " + decodeBig(valueList));
            }
        }
    }

    private static String decodeBig(List<BigInteger> valueList) {
        int walk = 0;
        while (valueList.get(walk).equals(valueList.get(walk + 1))) {
            walk++;
        }
        BigInteger a2 = valueList.get(walk).gcd(valueList.get(walk + 1));

        List<BigInteger> encodedChar = new ArrayList<>(Arrays.asList(
                valueList.get(walk).divide(a2),
                a2,
                valueList.get(walk + 1).divide(a2)
        ));

        for (int i = walk - 1; i >= 0; i--) encodedChar.add(0, encodedChar.get(1));

        for (int i = walk + 2; i < valueList.size(); i++) {
            BigInteger next = valueList.get(i).divide(encodedChar.get(encodedChar.size() - 1));
            encodedChar.add(next);
        }

        List<BigInteger> sortedEncodeVal =
                encodedChar.stream()
                           .distinct()
                           .sorted()
                           .collect(Collectors.toList());

        Map<BigInteger, Character> decodeMap = new HashMap<>();
        for (int i = 0; i < sortedEncodeVal.size(); i++)
            decodeMap.put(sortedEncodeVal.get(i), (char) ('A' + i));
        StringBuilder sb = new StringBuilder();
        for (BigInteger encodedVal : encodedChar) sb.append(decodeMap.get(encodedVal));
        return sb.toString();
    }

    private static String decodeSmall(List<Integer> valueList) {
        int walk = 0;
        while (valueList.get(walk).equals(valueList.get(walk + 1))) {
            walk++;
        }
        int a2 = GCD(valueList.get(walk), valueList.get(walk + 1));

        List<Integer> encodedChar = new ArrayList<>(Arrays.asList(
                valueList.get(walk) / a2,
                a2,
                valueList.get(walk + 1) / a2
        ));

        for (int i = walk - 1; i >= 0; i--) encodedChar.add(0, encodedChar.get(1));

        for (int i = walk + 2; i < valueList.size(); i++) {
            int next = valueList.get(i) / encodedChar.get(encodedChar.size() - 1);
            encodedChar.add(next);
        }

        List<Integer> sortedEncodeVal =
                encodedChar.stream()
                           .distinct()
                           .sorted()
                           .collect(Collectors.toList());

        Map<Integer, Character> decodeMap = new HashMap<>();
        for (int i = 0; i < sortedEncodeVal.size(); i++)
            decodeMap.put(sortedEncodeVal.get(i), (char) ('A' + i));
        StringBuilder sb = new StringBuilder();
        for (Integer encodedVal : encodedChar) sb.append(decodeMap.get(encodedVal));
        return sb.toString();
    }

    private static int GCD(int a, int b) {
        if (a == 0 && b == 0) return 0;
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}
