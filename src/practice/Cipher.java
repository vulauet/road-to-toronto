package practice;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cipher {
    static String cipher(int k, String s) {
        StringBuilder sb = new StringBuilder();
        List<Integer> encodedDigits =
                s.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        List<Integer> decodedDigits = new ArrayList<>();
        sb.append(s.charAt(0));
        decodedDigits.add(encodedDigits.get(0));
        for (int i = 1; i < s.length() - k + 1; i++) {
            int nextVal = encodedDigits.get(i) ^ encodedDigits.get(i - 1);
            if (i - k >= 0) nextVal ^= decodedDigits.get(i - k);
            sb.append(nextVal);
            decodedDigits.add(nextVal);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Assert.assertEquals("1001010", cipher(4, "1110100110"));
        Assert.assertEquals("101111", cipher(2, "1110001"));
        Assert.assertEquals("10000101", cipher(3,"1110011011"));
    }
}
