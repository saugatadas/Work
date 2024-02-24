import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
    private Digraph G;
    private BreadthFirstDirectedPaths[] bfs;
    private int countV;

    public SAP(Digraph G) {
        this.G = G;
        this.countV = G.V();
        this.bfs = new BreadthFirstDirectedPaths[countV];
    }


    private void setupBfs(int u) {
        if (bfs[u] == null)
            bfs[u] = new BreadthFirstDirectedPaths(G, u);
    }

    private void freeBfs(int u) {
        bfs[u] = null;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if ((v<0 || v>=countV) || (w<0 || w>=countV))
            throw new IndexOutOfBoundsException();

        setupBfs(v);
        setupBfs(w);

        int max = Integer.MAX_VALUE;
        for (int i=0; i<countV; i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int m = bfs[v].distTo(i);
                int n = bfs[w].distTo(i);
                int l = m + n;
                if (l < max)
                    max = l;
            }
        }

        freeBfs(v);
        freeBfs(w);

        if (max != Integer.MAX_VALUE)
            return max;
        return -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if ((v<0 || v>=countV) || (w<0 || w>=countV))
            throw new IndexOutOfBoundsException();

        int max = Integer.MAX_VALUE;
        int ancestor = -1;
        setupBfs(v);
        setupBfs(w);
        for (int i=0; i<countV; i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int m = bfs[v].distTo(i);
                int n = bfs[w].distTo(i);
                int l = m + n;
                if (l < max) {
                    max = l;
                    ancestor = i;
                }
            }
        }
        freeBfs(v);
        freeBfs(w);
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v==null || w==null)
            throw new NullPointerException();

        int max = Integer.MAX_VALUE;

        for (int p:v) {
            for (int q:w) {
                int l = length(p, q);
                if (l!=-1 && l<max)
                    max = l;
            }
        }

        if (max == Integer.MAX_VALUE)
            return -1;
        return max;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v==null || w==null)
            throw new NullPointerException();

        int max = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int p:v) {
            for (int q:w) {
                int l = length(p, q);
                if (l!=-1 && l<max) {
                    max = l;
                    ancestor = ancestor(p, q);
                }
            }
        }

        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
