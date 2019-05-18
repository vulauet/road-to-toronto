import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InteractiveSolution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        int f = in.nextInt();
        for (int i = 0; i < t; ++i) {
            solve(in);
            String result = in.next();
            if (result.equals("N")) System.exit(1);
        }

    }

    private static void solve(Scanner in) {

    }
}
