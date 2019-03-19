package practice;

public class IceCreamParlor {
    static int[] icecreamParlor(int m, int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == m) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
