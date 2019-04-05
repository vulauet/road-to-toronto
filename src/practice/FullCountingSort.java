package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FullCountingSort {
    static void countSort(Map<Integer, List<String>> arrMap) {
        String result = arrMap.keySet()
                              .stream()
                              .sorted()
                              .flatMap(i -> arrMap.get(i).stream())
                              .collect(Collectors.joining(" "));
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Map<Integer, List<String>> arrMap = new HashMap<>();
        IntStream.range(0, n).forEach(i -> {
            try {
                String[] nextInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                int order = Integer.parseInt(nextInput[0]);
                String s = i < n / 2 ? "-" : nextInput[1];
                List<String> curList = arrMap.getOrDefault(order, new ArrayList<>());
                curList.add(s);
                arrMap.put(order, curList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        countSort(arrMap);

        bufferedReader.close();
    }
}
