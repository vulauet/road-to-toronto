package practice.dp;

import java.util.Arrays;
import java.util.Collections;

public class ConferenceRoomSchedule {
    public static void main(String[] args) {

    }

    private static int lis(Conference[] conferences) {
        Integer[] L = new Integer[conferences.length];
        for (int i = 0; i < conferences.length; i++) {
            L[i] = 1;
            for (int j = 0; j < conferences.length; j++) {
                if (i != j && conferences[j].end <= conferences[i].start) {
                    L[i] = Math.max(L[i], L[j] + 1);
                }
            }
        }
        return Collections.max(Arrays.asList(L));
    }

    private static class Conference {
        int start;
        int end;
    }
}
