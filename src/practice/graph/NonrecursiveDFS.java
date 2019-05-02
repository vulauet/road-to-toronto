package practice.graph;

import java.util.Iterator;
import java.util.Stack;

public class NonrecursiveDFS {
    private boolean[] marked;  // marked[v] = is there an s-v path?
    /**
     * Computes the vertices connected to the source vertex {@code s} in the graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public NonrecursiveDFS(Graph G, int s) {
        marked = new boolean[G.V()];

        validateVertex(s);

        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                // System.out.printf("check %d\n", w);
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    // edgeTo[w] = v;
                    stack.push(w);
                    // System.out.printf("dfs(%d)\n", w);
                }
            }
            else {
                // System.out.printf("%d done\n", v);
                stack.pop();
            }
        }
    }

    /**
     * Is vertex {@code v} connected to the source vertex {@code s}?
     * @param v the vertex
     * @return {@code true} if vertex {@code v} is connected to the source vertex {@code s},
     *    and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Unit tests the {@code NonrecursiveDFS} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Graph G = new Graph(in);
//        int s = Integer.parseInt(args[1]);
//        NonrecursiveDFS dfs = new NonrecursiveDFS(G, s);
//        for (int v = 0; v < G.V(); v++)
//            if (dfs.marked(v))
//                System.out.print(v + " ");
//        System.out.println();
    }


}

