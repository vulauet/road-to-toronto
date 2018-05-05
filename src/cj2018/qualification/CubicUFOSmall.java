package cj2018.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CubicUFOSmall {
    private final double a;
    private Point p1 = new Point(0.5, 0, 0);
    private Point p2 = new Point(0, 0.5, 0);
    private Point p3 = new Point(0, 0, 0.5);


    public CubicUFOSmall(double a) {
        this.a = a;
    }

    public void answer() {
        double alpha = Math.PI / 4 - Math.acos(a / Math.sqrt(2));
        p1.x = 0.5 * Math.cos(alpha);
        p1.y = 0.5 * Math.sin(alpha);
        p2.x = -0.5 * Math.sin(alpha);
        p2.y = 0.5 * Math.cos(alpha);

        if (Math.abs(alpha - 0) > 10e-6) {

            System.out.println(p1.x + " " + p1.y + " 0");
            System.out.println(p2.x + " " + p2.y + " 0");
            System.out.println("0 0 0.5");
        } else {
            System.out.println("0.5 0 0");
            System.out.println("0 0.5 0");
            System.out.println("0 0 0.5");

        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            double a = in.nextDouble();
            CubicUFOSmall cubicUFOSmall = new CubicUFOSmall(a);
            System.out.println("Case #" + i + ":");
            cubicUFOSmall.answer();

        }
    }

    private class Point {
        private double x;
        private double y;
        private double z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
