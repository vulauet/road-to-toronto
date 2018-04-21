package helpers;

import java.util.Arrays;
import java.util.List;

public class PlayGround {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(5, 10, 20, 49, 100, 12);
        for (Integer A : integers) {

            findPair(A);
        }
    }

    private static void findPair(int A) {
        int a = A;
        int b = 1;
        int minDiff = a - b;
        for (int j = 2; j < A / 2; j++) {
            if (A % j == 0 && Math.abs(A - 2 * j) < minDiff) {
                a = j;
                b = A / j;
                minDiff = Math.abs(A - 2 * j);
            }
        }
        System.out.println(a + " " + b);
    }
}
