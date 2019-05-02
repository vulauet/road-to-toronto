package practice.graph;

public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    /**
     * Computes a shortest paths tree from each vertex to to every other vertex in
     * the edge-weighted digraph {@code G}.
     * @param G the edge-weighted digraph
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all  = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }

    /**
     * Returns a shortest path from vertex {@code s} to vertex {@code t}.
     * @param  s the source vertex
     * @param  t the destination vertex
     * @return a shortest path from vertex {@code s} to vertex {@code t}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public Iterable<DirectedEdge> path(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].pathTo(t);
    }

    /**
     * Is there a path from the vertex {@code s} to vertex {@code t}?
     * @param  s the source vertex
     * @param  t the destination vertex
     * @return {@code true} if there is a path from vertex {@code s}
     *         to vertex {@code t}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public boolean hasPath(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return dist(s, t) < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the length of a shortest path from vertex {@code s} to vertex {@code t}.
     * @param  s the source vertex
     * @param  t the destination vertex
     * @return the length of a shortest path from vertex {@code s} to vertex {@code t};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * @throws IllegalArgumentException unless {@code 0 <= t < V}
     */
    public double dist(int s, int t) {
        validateVertex(s);
        validateVertex(t);
        return all[s].distTo(t);
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = all.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    /**
     * Unit tests the {@code DijkstraAllPairsSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read edge-weighted digraph
//        In in = new In(args[0]);
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
//
//        // compute shortest paths between all pairs of vertices
//        DijkstraAllPairsSP spt = new DijkstraAllPairsSP(G);
//
//        // print all-pairs shortest path distances
//        System.out.printf("  ");
//        for (int v = 0; v < G.V(); v++) {
//            System.out.printf("%6d ", v);
//        }
//        System.out.println();
//        for (int v = 0; v < G.V(); v++) {
//            System.out.printf("%3d: ", v);
//            for (int w = 0; w < G.V(); w++) {
//                if (spt.hasPath(v, w)) System.out.printf("%6.2f ", spt.dist(v, w));
//                else System.out.printf("  Inf ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//        // print all-pairs shortest paths
//        for (int v = 0; v < G.V(); v++) {
//            for (int w = 0; w < G.V(); w++) {
//                if (spt.hasPath(v, w)) {
//                    System.out.printf("%d to %d (%5.2f)  ", v, w, spt.dist(v, w));
//                    for (DirectedEdge e : spt.path(v, w))
//                        System.out.print(e + "  ");
//                    System.out.println();
//                }
//                else {
//                    System.out.printf("%d to %d no path\n", v, w);
//                }
//            }
//        }
    }
}
