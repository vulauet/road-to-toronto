package practice;

import java.io.*;

public class SumXor {
    static long sumXor(long n) {
        int countZero = 0;
        String binarizedN = Long.toBinaryString(n);
        for (int i = 1; i < binarizedN.length(); i++)
            if (binarizedN.charAt(i) == '0')
                ++countZero;
        return 1L << countZero;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(sumXor(0));
        System.out.println(sumXor(4));
        System.out.println(sumXor(5));
        System.out.println(sumXor(10));

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        long n = Long.parseLong(bufferedReader.readLine().trim());
//
//        long result = sumXor(n);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}
