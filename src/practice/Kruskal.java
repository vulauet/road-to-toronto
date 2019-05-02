package practice;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Kruskal {


    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */
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

    private static class UF {

        Integer[] roots;

        public UF(int gNodes) {
            roots = IntStream.range(0, gNodes).boxed().toArray(Integer[]::new);
        }

        public void union(int to, int from) {
            if (!connected(to, from)) {
                Integer fromRoot = roots[from];
                for (int i = 0; i < roots.length; i++) {
                    if (roots[i].equals(fromRoot)) {
                        roots[i] = roots[to];
                    }
                }
            }
        }

        private boolean connected(int to, int from) {
            return roots[to].equals(roots[from]);
        }
    }

    public static int kruskals(
            int gNodes,
            List<Integer> gFrom,
            List<Integer> gTo,
            List<Integer> gWeight
    ) {

        Queue<Edge> mst = new LinkedList<>();
        PriorityQueue<Edge> pq =
                new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        UF uf = new UF(gNodes + 1);
        for (int i = 0; i < gFrom.size(); i++)
            pq.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));

        while (!pq.isEmpty() && mst.size() < gNodes - 1) {
            Edge edge = pq.poll();
            if (!uf.connected(edge.to, edge.from)) {
                uf.union(edge.to, edge.from);
                mst.add(edge);
            }
        }
        return mst.stream().mapToInt(Edge::getWeight).sum();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges =
                bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight =
                        bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Kruskal.kruskals(gNodes, gFrom, gTo, gWeight);
        System.out.println(res);
        bufferedWriter.write(String.valueOf(res));
        // Write your code here.

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
