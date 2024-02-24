import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    private boolean solvable;
    private boolean twinsolvable;
    private Stack<Board> res;
    private class searchNode implements Comparable<searchNode> {
        private int weight;
        private int moves;
        private Board current;
        private searchNode prev;

        public searchNode(Board board, int moves, searchNode prev) {
            this.moves = moves;
            this.weight = board.manhattan() + moves;
            this.current = board;
            this.prev = prev;
        }

        public int compareTo (searchNode that) {
            return this.weight - that.weight;
        }
    }

    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();
        solvable = false;
        twinsolvable = false;

        res = new Stack<>();

        MinPQ<searchNode> pq = new MinPQ<>();
        MinPQ<searchNode> twinpq = new MinPQ<>();

        pq.insert(new searchNode(initial, 0, null));
        twinpq.insert(new searchNode(initial.twin(), 0, null));

        while(!solvable && !twinsolvable) {
            searchNode n = pq.delMin();
            solvable = n.current.isGoal();

            if (solvable) {
                res.push(n.current);
                while(n.prev!=null) {
                    res.push(n.prev.current);
                    n = n.prev;
                }
                break;
            }

            for (Board b : n.current.neighbors()) {
                if (n.prev == null || !n.prev.current.equals(b)) {
                    pq.insert(new searchNode(b, n.moves+1, n));
                }
            }

            searchNode n1 = twinpq.delMin();
            twinsolvable = n1.current.isGoal();
            for (Board b : n1.current.neighbors()) {
                if (n1.prev == null || !n1.prev.current.equals(b)) {
                    twinpq.insert(new searchNode(b, n1.moves+1, n1));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return res.size();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return res;
    }

    // test client (see below)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{1, 2}, {0, 3}});
        Solver s = new Solver(board);
        if (s.isSolvable()) {
            for (Board b : s.solution())
                System.out.println(b);
        }
        else {
            System.out.println("No solution");
        }
    }
}
