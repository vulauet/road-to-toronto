package practice;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

public class AliceAndBobSillyGame {
    static String sillyGame(int n) {
        /*
         * Write your code here.
         */
        boolean[] sieve = sieve(n);
        int countPrime = 0;
        for (boolean isPrime : sieve) if (isPrime) ++countPrime;
        return countPrime % 2 == 0 ? "Bob" : "Alice";
    }

    static boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        int m = (int) Math.sqrt(n);

        for (int i = 2; i <= m; i++)
            if (prime[i])
                for (int k = i * i; k <= n; k += i)
                    prime[k] = false;

        return prime;
    }



    public static void main(String[] unsorted) {
        Arrays.stream(unsorted)
              .sorted(Comparator.comparingInt(String::length).thenComparing((s1, s2) -> {
                  for (int i = 0; i < s1.length(); i++) {
                      if (s1.charAt(i) != s2.charAt(i))
                          return s1.charAt(i) - s2.charAt(i);
                  }
                  return 0;
              }))
              .toArray(String[]::new);



        for (int i = 0; i < unsorted.length - 1; i++) {
            for (int j = i + 1; j < unsorted.length; j++) {
                if (unsorted[i].length() > unsorted[j].length()
                        || unsorted[i].compareTo(unsorted[j]) > 0) {
                    String tmp = unsorted[j];
                    unsorted[j] = unsorted[i];
                    unsorted[i] = tmp;
                }
            }
        }

        Assert.assertEquals("Bob", sillyGame(1));
        Assert.assertEquals("Alice", sillyGame(2));
        Assert.assertEquals("Alice", sillyGame(5));
    }
}
