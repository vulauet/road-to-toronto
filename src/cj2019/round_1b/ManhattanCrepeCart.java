package cj2019.round_1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ManhattanCrepeCart {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int p = in.nextInt();
            int q = in.nextInt();
            List<Person> people = new ArrayList<>();
            for (int j = 0; j < p; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                String next = in.next();
                people.add(new Person(x, y, next));
            }

//            int[] pos = solveSmall(p, q, people);
            int[] pos = solveBig(p, q, people);
            System.out.println("Case #" + i + ": " + pos[0] + " " + pos[1]);
        }
    }

    private static int[] solveBig(int p, int q, List<Person> people) {
        int[] east = new int[q + 1];
        int[] west = new int[q + 1];
        int[] north = new int[q + 1];
        int[] south = new int[q + 1];

        for (Person person : people) {
            switch (person.direction) {
                case "E":
                    if (person.x < q)
                        east[person.x + 1] += 1;
                    break;
                case "W":
                    if (person.x > 0) west[person.x - 1] += 1;
                    break;
                case "N":
                    if (person.y < q) north[person.y + 1] += 1;
                    break;
                case "S":
                    if (person.y > 0) south[person.y - 1] += 1;
                    break;
            }
        }

        for (int i = 1; i < q + 1; i++) {
            east[i] += east[i - 1];
            north[i] += north[i - 1];
        }

        for (int i = q - 1; i >= 0; i--) {
            west[i] += west[i + 1];
            south[i] += south[i + 1];
        }

        List<Integer> toX = new ArrayList<>();
        List<Integer> toY = new ArrayList<>();
        for (int i = 0; i <= q; i++) {
            toX.add(east[i] + west[i]);
            toY.add(north[i] + south[i]);
        }
        return new int[]{
                toX.indexOf(Collections.max(toX)),
                toY.indexOf(Collections.max(toY))
        };
    }

    private static int[] solveSmall(int p, int q, List<Person> people) {

        int[][] grid = new int[q + 1][q + 1];

        for (Person person : people) {
            switch (person.direction) {
                case "E":
                    for (int i = person.x + 1; i < q + 1; i++) {
                        for (int j = 0; j < q + 1; j++) {
                            grid[i][j] += 1;
                        }
                    }
                    break;
                case "W":
                    for (int i = person.x - 1; i >= 0; i--) {
                        for (int j = 0; j < q + 1; j++) {
                            grid[i][j] += 1;
                        }
                    }
                    break;
                case "N":
                    for (int i = person.y + 1; i < q + 1; i++) {
                        for (int j = 0; j < q + 1; j++) {
                            grid[j][i] += 1;
                        }
                    }
                    break;
                case "S":
                    for (int i = person.y - 1; i >= 0; i--) {
                        for (int j = 0; j < q + 1; j++) {
                            grid[j][i] += 1;
                        }
                    }
                    break;

            }
        }

        int[] pos = new int[]{0, 0};
        for (int i = 0; i < q + 1; i++) {
            for (int j = 0; j < q + 1; j++) {
                System.out.print(grid[i][j] + " ");
                if (grid[i][j] > grid[pos[0]][pos[1]]) {
                    pos = new int[]{i, j};
                }
            }
            System.out.println();
        }
        return pos;
    }

    private static class Person {
        int x;
        int y;
        String direction;

        public Person(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
