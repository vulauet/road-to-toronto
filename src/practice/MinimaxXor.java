package practice;

import org.junit.Assert;

import java.util.*;

public class MinimaxXor {

    static class Pair implements Comparable<Pair> {
        int a;
        int b;
        int xor;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
            this.xor = a ^ b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a &&
                    b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public int compareTo(Pair that) {
            return Integer.compare(this.xor, that.xor);
        }
    }

    static int anotherMinimaxProblem(int[] a) {
        List<Pair> pairList = new ArrayList<>();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                pairList.add(new Pair(a[i], a[j]));
            }
        }
        Collections.sort(pairList);
        Set<Integer> elems = new HashSet<>();
        for (Pair pair : pairList) {
            System.out.println(pair.a + "," + pair.b + "," + pair.xor);
            elems.add(pair.a);
            elems.add(pair.b);
            if (elems.size() == a.length) {
                return pair.xor;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        Assert.assertEquals(2, anotherMinimaxProblem(new int[]{1, 2, 3}));
//        Assert.assertEquals(5, anotherMinimaxProblem(new int[]{1, 2, 3, 4}));
        Assert.assertEquals(4, anotherMinimaxProblem(new int[]{12, 8, 9, 11, 14}));
    }

}
