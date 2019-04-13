package cj2019.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17);

        Map<Integer, String> prime2Msg = new HashMap<>();
        for (Integer prime : primes) {
            prime2Msg.put(prime, constructMsg(prime));
        }
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] sieve = sieve(m);
        for (int i = 1; i <= t; ++i) {
            Map<Integer, Integer> moduloMap = new HashMap<>();
            for (Integer prime : primes) {
                System.out.println(prime2Msg.get(prime));
                int sum = 0;
                for (int j = 0; j < 18; j++) {
                    sum += in.nextInt();
                }
                moduloMap.put(prime, sum % prime);
            }

            int init = 1;
            for (Integer prime : moduloMap.keySet()) {
                if (moduloMap.get(prime) == 0) init *= prime;
            }

            if (init == 1) {
                for (int j = 19; j <= m; j++) {
                    if (sieve[j] && isResult(moduloMap, j)) {
                        System.out.println(j);
                        break;
                    }
                }
            } else {
                for (int j = 1; j <= m; j++) {
                    if (init * j > m) break;
                    if (isResult(moduloMap, j * init)) {
                        System.out.println(j * init);
                        break;
                    }
                }
            }

            int result = in.nextInt();
            if (result == -1) System.exit(-1);

        }
    }

    private static boolean isResult(Map<Integer, Integer> moduloMap, int candidate) {
        for (Integer prime : moduloMap.keySet()) {
            if (candidate % prime != moduloMap.get(prime)) {
                return false;
            }
        }
        return true;
    }

    private static String constructMsg(Integer prime) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            sb.append(prime).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        int m = (int) Math.sqrt(n);
        for (int i = 2; i <= m; i++)
            if (prime[i])
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;
        return prime;
    }
}
