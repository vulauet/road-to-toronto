package practice;

import java.util.Scanner;

public class MaximizingXOR {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        int L = scan.nextInt();
        int R = scan.nextInt();
        scan.close();

        int xored  = L ^ R;
        System.out.println(xored);

        System.out.println(Integer.toBinaryString(L));
        System.out.println(Integer.toBinaryString(R));
        System.out.println(Integer.toBinaryString(xored));

        int significantBit = 31 - Integer.numberOfLeadingZeros(xored);

        System.out.println(significantBit);

        int result = (1 << (significantBit + 1)) - 1;

        System.out.println(result);
        System.out.println(Integer.toBinaryString(result));
    }
}
