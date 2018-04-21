package io_utils;

/**
 * Created by vula on 02/04/2017.
 */
public class CodeJamUtils {

    private static boolean DEBUG_ON = true;

    public static void setDebugOn(boolean flag) {
        DEBUG_ON = flag;
    }

    public static boolean isNumeric(String numberAsString) {
        return numberAsString.matches("\\d+");
    }

    public static void printLine(String line) {
        if (DEBUG_ON) System.out.println(line);
    }



}
