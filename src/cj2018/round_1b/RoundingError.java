package cj2018.round_1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class RoundingError {

    private int numPeople;
    private int numLang;
    private ArrayList<Integer> countPerLang;
    private static Map<List<Integer>, Integer> scoreMap = new HashMap<>();

    public RoundingError(int numPeople, int numLang, ArrayList<Integer> countPerLang) {
        this.numPeople = numPeople;
        this.numLang = numLang;
        this.countPerLang = countPerLang;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int L = in.nextInt();

            ArrayList<Integer> countPerLang = new ArrayList<>();
            for (int j = 0; j < L; j++) {
                countPerLang.add(in.nextInt());
            }

            scoreMap = new HashMap<>();
            System.out.println("Case #" + i + ": " + new RoundingError(N, L, countPerLang).answer());
        }
    }

    public int answer() {
        int answeredCount = countAnswered(countPerLang);

        double scorePerAnswer = 100.0 / numPeople;
        if (answeredCount == numPeople) {
            return calScore(scorePerAnswer, countPerLang);
        } else {
            if (Math.round(scorePerAnswer) - scorePerAnswer > 10e-6) {
                int curScore = calScore(scorePerAnswer, countPerLang);
                int maxAddScore = (numPeople - answeredCount) * (int) Math.round(scorePerAnswer);
                return curScore + maxAddScore;
            } else {

                int unAnswered = numPeople - answeredCount;
                while (unAnswered > 0) {
                    int curUnAnswered = unAnswered;
                    for (int i = 0; i < countPerLang.size(); i++) {
                        double scoreForIth = countPerLang.get(i) * scorePerAnswer;
                        if (scoreForIth > Math.round(scoreForIth)) {
                            double scoreForIthIncr = (countPerLang.get(i) + 1) * scorePerAnswer;
                            if (scoreForIthIncr < Math.round(scoreForIthIncr)) {
                                countPerLang.set(i, countPerLang.get(i) + 1);
                                unAnswered--;
                                break;
                            }
                        }
                    }
                    if (unAnswered == curUnAnswered) {
                        countPerLang.add(1);
                        unAnswered--;
                    }
                }


//                List<Integer> answers = new ArrayList<>();

//                Set<List<Integer>> allCombination = generateAllCombination(numPeople, countPerLang, answeredCount);
//                for (List<Integer> countByLang : allCombination) {
//                    answers.add(calScore(scorePerAnswer, countByLang));
//                }

//                for (int i = 0; i < countPerLang.size(); i++) {
//                    double scoreForIth = countPerLang.get(i) * scorePerAnswer;
//                    if (scoreForIth - Math.round(scoreForIth) > 10e-6) {
//                        List<Integer> countPerLangIncrease = new ArrayList<>(countPerLang);
//                        countPerLangIncrease.set(i, countPerLangIncrease.get(i) + 1);
//                        if (!scoreMap.containsKey(countPerLangIncrease)) {
//                            scoreMap.put(countPerLangIncrease, new RoundingError(numPeople, numLang, countPerLangIncrease).answer());
//                        }
//                        answers.add(scoreMap.get(countPerLangIncrease));
//                    }
//                }
//                List<Integer> countPerLangIncrease = new ArrayList<>(countPerLang);
//                countPerLangIncrease.add(1);
//                if (!scoreMap.containsKey(countPerLangIncrease)) {
//                    scoreMap.put(countPerLangIncrease, new RoundingError(numPeople, numLang, countPerLangIncrease).answer());
//                }
//                answers.add(scoreMap.get(countPerLangIncrease));
//                return Collections.max(answers);
                return calScore(scorePerAnswer, countPerLang);
            }
        }
    }

    private int countAnswered(List<Integer> countPerLang) {
        int answeredCount = 0;
        for (Integer count : countPerLang) {
            answeredCount += count;
        }
        return answeredCount;
    }

    private Set<List<Integer>> generateAllCombination(int numPeople, List<Integer> countPerLang, int answeredCount) {
        Set<List<Integer>> allCombination = new HashSet<>();
        allCombination.add(countPerLang);
        int notYetAnswer = numPeople - answeredCount;
        while (notYetAnswer > 0) {
            Set<List<Integer>> combinations = new HashSet<>();
            for (List<Integer> combination : allCombination) {
                for (int i = 0; i < combination.size(); i++) {
                    List<Integer> incr = new ArrayList<>(combination);
                    incr.set(i, incr.get(i) + 1);
                    combinations.add(incr);
                }
                List<Integer> incr = new ArrayList<>(combination);
                incr.add(1);
                combinations.add(incr);
            }
            allCombination = new HashSet<>(combinations);
            notYetAnswer--;
        }
        return allCombination;
    }


    private int calScore(double scorePerAnswer, List<Integer> countPerLang) {
        int score = 0;
        for (Integer countByLang : countPerLang) {
            score += Math.round(scorePerAnswer * countByLang);
        }
        return score;
    }

}
