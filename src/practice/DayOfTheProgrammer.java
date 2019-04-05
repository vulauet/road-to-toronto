package practice;

import org.junit.Assert;

import java.util.function.Function;

public class DayOfTheProgrammer {

    enum Calendar {
        JULIAN(Calendar::isJulianLeapYear),
        GREGORIAN(Calendar::isGregorianLeapYear),
        Y1918(i -> false);

        private Function<Integer, Boolean> isLeapYear;

        Calendar(Function<Integer, Boolean> isLeapYear) {
            this.isLeapYear = isLeapYear;
        }

        private static boolean isGregorianLeapYear(int year) {
            return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        }

        private static boolean isJulianLeapYear(int year) {
            return year % 4 == 0;
        }

        public static Calendar getInstance(int year) {
            if (year == 1918) return Y1918;
            else if (year < 1918) return JULIAN;
            else return GREGORIAN;
        }

        public String dayOfProgrammer(int year) {
            return isLeapYear.apply(year) ? "12.09." + year : "13.09." + year;
        }
    }

    static String dayOfProgrammer(int year) {
        return Calendar.getInstance(year).dayOfProgrammer(year);
    }

    public static void main(String[] args) {
        Assert.assertEquals("13.09.2017", dayOfProgrammer(2017));
        Assert.assertEquals("12.09.2016", dayOfProgrammer(2016));
        Assert.assertEquals("12.09.1800", dayOfProgrammer(1800));
    }
}
