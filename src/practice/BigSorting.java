package practice;

import java.util.*;

public class BigSorting {
    static String[] bigSorting(String[] unsorted) {
        Arrays.sort(
                unsorted,
                Comparator.comparingInt(String::length).thenComparing(String::compareTo)
        );

        return unsorted;
    }

    private static Comparator<String> bigSortComparator() {
        return (s1, s2) -> {
            if (s1.length() != s2.length()) return s1.length() - s2.length();
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) return s1.charAt(i) - s2.charAt(i);
            }
            return 0;
        };
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Map<String, Integer> stringCount = new HashMap<>();

        PriorityQueue<String> pQueue = new PriorityQueue<>(bigSortComparator());

        for (int i = 0; i < n; i++) {
            String unsortedItem = scanner.nextLine();
            if (!stringCount.containsKey(unsortedItem)) {
                pQueue.add(unsortedItem);
                stringCount.put(unsortedItem, 1);
            } else {
                stringCount.put(unsortedItem, stringCount.get(unsortedItem) + 1);
            }

        }

        String[] result = new String[n];
        int walk = 0;
        while (!pQueue.isEmpty()) {
            String cur = pQueue.remove();
            for (int i = 0; i < stringCount.get(cur); i++) {
                result[walk++] = cur;
            }
        }
//        for (int i = 0; i < pQueue.size(); i++) {
//            result[i] = pQueue.remove();
//            pQueue.remove(result[i]);
//        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);

            if (i != result.length - 1) {
                System.out.println();
            }
        }


        scanner.close();
    }
}
