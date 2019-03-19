package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

    private static void writePermutationOfN(List<Integer> list) {
        writePermutationOfN(list, "");
    }

    private static void writePermutationOfN(List<Integer> list, String steps) {
        if (list.isEmpty()) {
            System.out.println(steps);
        } else {
            for (int j = 0; j < list.size(); j++) {
                List<Integer> listClone = new ArrayList<>(list);
                String step = String.format("%d ", listClone.get(j));
                listClone.remove(j);
                System.out.println(listClone);
                writePermutationOfN(listClone, steps + step);
            }
        }
    }

    public static void main(String[] args) {
        writePermutationOfN(Arrays.asList(1, 2, 3, 4));
    }
}
