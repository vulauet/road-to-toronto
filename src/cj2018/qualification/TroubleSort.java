package cj2018.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TroubleSort {
    private final List<Integer> evenList;
    private final List<Integer> oddList;

    public TroubleSort(List<Integer> evenList, List<Integer> oddList) {
        this.evenList = evenList;
        this.oddList = oddList;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            List<Integer> evenList = new ArrayList<>();
            List<Integer> oddList = new ArrayList<>();
            int walk = 0;
            while (walk < n) {
                evenList.add(in.nextInt());
                walk++;
                if (walk < n) {
                    oddList.add(in.nextInt());
                    walk++;
                }
            }

            TroubleSort troubleSort = new TroubleSort(evenList, oddList);
            System.out.println("Case #" + i + ": " + troubleSort.answer());

        }
    }

    public String answer() {
        Collections.sort(evenList);
        Collections.sort(oddList);

        List<Integer> combine = new ArrayList<>();
        for (int i = 0; i < oddList.size(); i++) {
            combine.add(evenList.get(i));
            combine.add(oddList.get(i));
        }
        if (evenList.size() > oddList.size()) combine.add(evenList.get(evenList.size() - 1));
        for (int i = 0; i < combine.size() - 1; i++) {
            if (combine.get(i) > combine.get(i + 1)) return String.valueOf(i);
        }
        return "OK";
    }
}
