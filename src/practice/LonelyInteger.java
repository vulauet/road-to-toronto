package practice;

public class LonelyInteger {
    static int lonelyinteger(int[] a) {
        int result = 0;
        for (int i : a) {
            result = result ^ i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lonelyinteger(new int[]{1}));
        System.out.println(lonelyinteger(new int[]{1, 1, 2}));
        System.out.println(lonelyinteger(new int[]{0, 0, 1, 2, 1}));
    }
}
