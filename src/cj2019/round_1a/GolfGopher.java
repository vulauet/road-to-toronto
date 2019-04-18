package cj2019.round_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GolfGopher {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        List<Integer> primes = Arrays.asList(5, 7, 11, 13, 17, 18);
        int product = primes.stream().reduce((p1, p2) -> p1 * p2).get();
        List<Integer> M =
                primes.stream()
                        .mapToInt(p -> product / p)
                        .boxed()
                        .collect(Collectors.toList());

        List<Integer> y =
                IntStream.range(0, primes.size())
                        .map(i -> inverseModulo(M.get(i), primes.get(i)))
                        .boxed()
                        .collect(Collectors.toList());

        List<Integer> myProduct = IntStream.range(0, primes.size())
                .map(i -> M.get(i) * y.get(i))
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, String> prime2Msg = new HashMap<>();
        for (Integer prime : primes) {
            prime2Msg.put(prime, constructMsg(prime));
        }

        int t = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            List<Integer> a = new ArrayList<>();
            for (Integer prime : primes) {
                System.out.println(prime2Msg.get(prime));
                int sum = 0;
                for (int j = 0; j < 18; j++)
                    sum += in.nextInt();
                a.add(sum % prime);
            }
            long result =
                    IntStream.range(0, primes.size())
                            .mapToLong(j -> (long) a.get(j) * myProduct.get(j))
                            .sum();
            System.out.println(result % (long) product);
            int res = in.nextInt();
            if (res == -1) System.exit(-1);
        }
    }

    private static int inverseModulo(int M, int m) {
        int reduceM = M % m;
        for (int i = 1; i < m; i++) {
            if (reduceM * i % m == 1) return i;
        }
        return 1 / 0;
    }

    private static String constructMsg(Integer prime) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            sb.append(prime).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
