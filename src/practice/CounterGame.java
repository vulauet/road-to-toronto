package practice;

public class CounterGame {

    static String counterGame(long n) {
        String binarizedN = Long.toBinaryString(n);
        int last1Index = -1;
        int count1 = 0;
        for (int i = 0; i < binarizedN.length(); i++) {
            if (binarizedN.charAt(i) == '1') {
                ++count1;
                last1Index = i;
            }
        }
        int countExeeding0 = binarizedN.length() - last1Index - 1;
        return (count1 + countExeeding0) % 2 == 0 ? "Louise" : "Richard";
    }

    public static void main(String[] args) {
        System.out.println(counterGame(6));
        System.out.println(counterGame(132));
        System.out.println(counterGame(1));
    }
}
