package practice;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Pairs {
    static int pairs(int k, int[] arr) {
        Set<Integer> arrSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        return (int) arrSet.stream().filter(i -> arrSet.contains(i - k)).count();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.binarySearch(new int[]{1, 5, 3, 4, 2}, 2));
//        Assert.assertEquals(3, pairs(1, new int[]{1, 2, 3, 4}));
//        Assert.assertEquals(3, pairs(2, new int[]{1, 5, 3, 4, 2}));
    }
}
