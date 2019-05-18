package cj2019.round_1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class RobotProgrammingStrategy {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t =
                in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int a = in.nextInt();
            ArrayList<String> programs = new ArrayList<>();
            for (int j = 0; j < a; j++) {
                programs.add(in.next());
            }
            System.out.println("Case #" + i + ": " + solveSmall(programs));
        }
    }

    public static String solveSmall(ArrayList<String> programs) {
        boolean[] contain = new boolean[3];
        reset(contain);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            if (programs.isEmpty()) return res.toString();
            for (String program : programs) {
                char c = program.charAt(i % program.length());
                switch (c) {
                    case 'P':
                        contain[0] = true;
                        break;
                    case 'R':
                        contain[1] = true;
                        break;
                    case 'S':
                        contain[2] = true;
                        break;
                }
                if (contain[0] && contain[1] && contain[2])
                    return "IMPOSSIBLE";
            }

            char next = 'P';
            int count = 1;

            if (contain[0] && contain[1]) {
                next = 'P';
                count = 2;
            } else if (contain[0] && contain[2]) {
                next = 'S';
                count = 2;
            } else if (contain[1] && contain[2]) {
                next = 'R';
                count = 2;
            }
            else if (contain[0]) next = 'S';
            else if (contain[1]) next = 'P';
            else if (contain[2]) next = 'R';
            reset(contain);
            res.append(next);

            if (count == 2) {
                List<String> lost = new ArrayList<>();
                for (String program : programs) {
                    if (next == 'P' && program.charAt(i % program.length()) == 'R'
                            || next == 'R' && program.charAt(i % program.length()) == 'S'
                            || next == 'S' && program.charAt(i % program.length()) == 'P') {
                        lost.add(program);
                    }
                }
                programs.removeAll(lost);
            }
        }
        return res.toString();
    }

    private static void reset(boolean[] contain) {
        for (int i = 0; i < contain.length; i++) {
            contain[i] = false;
        }
    }
}
