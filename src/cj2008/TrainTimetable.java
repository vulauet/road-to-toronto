package cj2008;

import io_utils.*;
import java.util.*;
import static io_utils.CodeJamOutput.writeOutput;


public class TrainTimetable {

    public static void main(String[] args) {
        CodeJamInput input = new CodeJamInput(new TrainTimetableParser());
        List<ICommonOutput> outputList = new ArrayList<>();
        for (int i = 0; i < input.getNumOfCase(); i++) {
            ICommonInput ithCase = input.getIthCase(i);
            TrainTimetableInput tti = (TrainTimetableInput) ithCase;
//            ICommonOutput output = new TrainTimetableOutput(tti);
            int[] solve = solve(tti);
            ICommonOutput output = new TrainTimetableOutput(solve[0], solve[1]);
            outputList.add(output);
        }
        writeOutput(outputList);
    }

    private static int[] solve(TrainTimetableInput tti) {
        int[] result = new int[2];
        int T = tti.getTurnAroundTime();
        List<TripNode> naTrips = tti.getNaTrips();
        List<TripNode> nbTrips = tti.getNbTrips();

        if (naTrips.size() == 0 || nbTrips.size() == 0)
            return new int[]{naTrips.size(), nbTrips.size()};

        naTrips.sort(TrainTimetable::compareByArriveTime);
        nbTrips.sort(TrainTimetable::compareByDepartTime);
        mapTrain(naTrips, nbTrips, T);
        naTrips.sort(TrainTimetable::compareByDepartTime);
        nbTrips.sort(TrainTimetable::compareByArriveTime);
        mapTrain(nbTrips, naTrips, T);

        for (TripNode trip : naTrips) if (trip.prev == null) result[0] += 1;
        for (TripNode trip : nbTrips) if (trip.prev == null) result[1] += 1;

//        printTrip(naTrips);
//        System.out.print("-------");
//        printTrip(nbTrips);

        return result;
    }

    private static void printTrip(List<TripNode> naTrips) {
        for (TripNode naTrip : naTrips) {
            System.out.println(naTrip.toString());
            if (naTrip.next != null) System.out.println(naTrip.next.toString());
            System.out.println();
        }
    }

    private static void mapTrain(List<TripNode> naTrips, List<TripNode> nbTrips, int T) {

        int cursorA = 0;
        TripNode walkA = naTrips.get(cursorA++);
        int cursorB = 0;
        TripNode walkB = nbTrips.get(cursorB++);


        while (true) {
            if (walkA.ready(T, walkB)) {
                walkA.next = walkB;
                walkB.prev = walkA;
                if (cursorA < naTrips.size()) walkA = naTrips.get(cursorA++);
                if (cursorB < nbTrips.size()) walkB = nbTrips.get(cursorB++);
            }
            while (!walkA.ready(T, walkB) && cursorB < nbTrips.size())
                walkB = nbTrips.get(cursorB++);
            if (walkA.ready(T, walkB)) {
                walkA.next = walkB;
                walkB.prev = walkA;
            }
            if (cursorA >= naTrips.size() || cursorB >= nbTrips.size()) break;
        }


    }

    private static int compareByDepartTime(TripNode node1, TripNode node2) {
        if (node1.departHour == node2.departHour)
            return Integer.compare(node1.departMin, node2.departMin);
        return Integer.compare(node1.departHour, node2.departHour);
    }

    private static int compareByArriveTime(TripNode node1, TripNode node2) {
        if (node1.arriveHour == node2.arriveHour)
            return Integer.compare(node1.arriveMin, node2.arriveMin);
        return Integer.compare(node1.arriveHour, node2.arriveHour);
    }


    private static class TrainTimetableParser implements ICommonInputParser {
        @Override
        public ICommonInput parse(Scanner scanner) {
            int turnAroundTime = scanner.nextInt();
            int NA = scanner.nextInt();
            int NB = scanner.nextInt();
            List<TripNode> naTrips = new ArrayList<>();
            for (int i = 0; i < NA; i++) {
                String depart = scanner.next();
                String arrive = scanner.next();
                naTrips.add(TripNode.parse(depart, arrive));
            }
            List<TripNode> nbTrips = new ArrayList<>();
            for (int i = 0; i < NB; i++) {
                String depart = scanner.next();
                String arrive = scanner.next();
                nbTrips.add(TripNode.parse(depart, arrive));
            }
            return new TrainTimetableInput(turnAroundTime, naTrips, nbTrips);
        }

    }

    private static class TrainTimetableInput implements ICommonInput {
        private int turnAroundTime;
        private List<TripNode> naTrips;
        private List<TripNode> nbTrips;

        public TrainTimetableInput(int turnAroundTime, List<TripNode> naTrips, List<TripNode> nbTrips) {
            this.turnAroundTime = turnAroundTime;
            this.naTrips = naTrips;
            this.nbTrips = nbTrips;
        }

        public int getTurnAroundTime() {
            return turnAroundTime;
        }

        public List<TripNode> getNaTrips() {
            return naTrips;
        }

        public List<TripNode> getNbTrips() {
            return nbTrips;
        }
    }

    private static class TripNode {
        private int departHour;
        private int departMin;
        private int arriveHour;
        private int arriveMin;
        private TripNode next;
        private TripNode prev;

        public TripNode(int departHour, int departMin, int arriveHour, int arriveMin) {
            this.departHour = departHour;
            this.departMin = departMin;
            this.arriveHour = arriveHour;
            this.arriveMin = arriveMin;
        }

        public boolean ready(int turnAroundTime, TripNode node) {
            int readyMin = arriveMin + turnAroundTime;
            int readyHour = arriveHour + readyMin / 60;
            readyMin = readyMin % 60;
            return readyHour < node.departHour
                    || (readyHour == node.departHour && readyMin <= node.departMin);
        }

        public static TripNode parse(String depart, String arrive) {
            String[] startTime = depart.split(":");
            String[] stopTime = arrive.split(":");
            int departHour = Integer.parseInt(startTime[0]);
            int departMin = Integer.parseInt(startTime[1]);
            int arriveHour = Integer.parseInt(stopTime[0]);
            int arriveMin = Integer.parseInt(stopTime[1]);
            return new TripNode(departHour, departMin, arriveHour, arriveMin);
        }

        @Override
        public String toString() {
            return "TripNode{" +
                    "departHour=" + departHour +
                    ", departMin=" + departMin +
                    ", arriveHour=" + arriveHour +
                    ", arriveMin=" + arriveMin +
                    '}';
        }
    }

    private static class TrainTimetableOutput implements ICommonOutput {

        private int trainFromA;
        private int trainFromB;

        //        public TrainTimetableOutput(ICommonInput input) {
        public TrainTimetableOutput(int trainFromA, int trainFromB) {
            this.trainFromA = trainFromA;
            this.trainFromB = trainFromB;
        }

        @Override
        public String toString() {
            return trainFromA + " " + trainFromB;
        }
    }
}
