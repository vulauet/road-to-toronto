package cj2018.qualification;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SavingUniverse {

    private int d;
    private String p;

    public SavingUniverse(int d, String p) {
        this.d = d;
        this.p = p;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int d = in.nextInt();
            String p = in.next();
            SavingUniverse savingUniverse = new SavingUniverse(d, p);
            int answer = savingUniverse.solve();
            if (answer >= 0) {
                System.out.println("Case #" + i + ": " + answer);
            } else {
                System.out.println("Case #" + i + ": " + "IMPOSSIBLE");

            }
        }

    }

    public int solve() {
        int hackCount = 0;
        while (calDam(p) > d) {
            String newP = hack(p);
            if (newP.equals(p)) {
                return -1;
            } else {
                p = newP;
                ++hackCount;
            }
        }
        return hackCount;
    }

    private String hack(String p) {
        for (int i = p.length() - 1; i > 0; i--) {
            if (p.charAt(i) == 'S' && p.charAt(i - 1) == 'C') {
                String newP = p.substring(0, i - 1) + 'S' + 'C';
                if (i + 1 < p.length()) newP = newP + p.substring(i + 1);
                return newP;
            }
        }
        return p;
    }

    private int calDam(String p) {
        int curDam = 1;
        int totalDam = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == 'C') {
                curDam *= 2;
            } else if (p.charAt(i) == 'S') {
                totalDam += curDam;
            }
        }
        return totalDam;
    }

}
