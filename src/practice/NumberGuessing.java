package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NumberGuessing {

    private int a;
    private int b;
    private int n;

    public NumberGuessing(int a, int b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();

            NumberGuessing solution = new NumberGuessing(a, b, n);

            System.out.println(solution.answer());
            String result = in.next();

            while (!result.equals("CORRECT")) {
                solution.adjust(result);
                System.out.println(solution.answer());
                result = in.next();
            }

//            System.out.print("Case #" + i + ": " + +" " +);
        }

    }

    private int answer() {
        int midPoint = getMidPoint();
        return midPoint > a ? midPoint : midPoint + 1;
    }

    private int getMidPoint() {
        return a / 2 + b / 2;
    }

    public void adjust(String result) {
        if (result.equals("TOO_SMALL")) {
            a = getMidPoint();
        } else {
            b = getMidPoint() - 1;
        }
    }


}
