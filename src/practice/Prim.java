package practice;

import java.io.*;
import java.util.*;

public class Prim {

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        int getWeight() {
            return weight;
        }
    }

    // Complete the prims function below.
    static int prims(int n, int[][] edges, int start) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            Edge e = new Edge(from, to, edge[2]);

            List<Edge> adjFrom = graph.getOrDefault(from, new ArrayList<>());
            adjFrom.add(e);
            graph.put(from, adjFrom);

            List<Edge> adjTo = graph.getOrDefault(to, new ArrayList<>());
            adjTo.add(e);
            graph.put(to, adjTo);
        }

        PriorityQueue<Edge> pq =
                new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        Queue<Edge> mst = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        visit(graph, start, pq, marked);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (marked[edge.from] && marked[edge.to]) continue;
            mst.add(edge);
            if (!marked[edge.from]) visit(graph, edge.from, pq, marked);
            if (!marked[edge.to]) visit(graph, edge.to, pq, marked);
        }
        return mst.stream().mapToInt(Edge::getWeight).sum();
    }

    private static void visit(
            Map<Integer, List<Edge>> graph,
            int v,
            PriorityQueue<Edge> pq,
            boolean[] marked
    ) {
        marked[v] = true;
        for (Edge edge : graph.get(v)) {
            pq.add(edge);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter =
//                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}

