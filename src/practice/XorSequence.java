package practice;

import org.junit.Assert;

import java.io.IOException;
import java.util.Scanner;

public class XorSequence {

    static long xorSequence(long l, long r) {
        return xorSequenceToN(r) ^ xorSequenceToN(l - 1);
    }

    private static long xorSequenceToN(long n) {
        switch((int) (n % 8))
        {
            case 0: case 1: return n;
            case 2: case 3: return 2;
            case 4: case 5: return n + 2;
            case 6: case 7: return 0;
            default:
                Assert.fail();
                return -1L;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter =
//                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] lr = scanner.nextLine().split(" ");

            long l = Long.parseLong(lr[0]);

            long r = Long.parseLong(lr[1]);

            long result = xorSequence(l, r);
            System.out.println(result);

//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
