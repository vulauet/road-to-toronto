package cj2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class YouCanGoYourOwnWay {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            String lydia = in.next();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < lydia.length(); j++)
                if (lydia.charAt(j) == 'E') sb.append('S');
                else sb.append('E');
            System.out.println("Case #" + i + ": " + sb.toString());
        }
    }
}
