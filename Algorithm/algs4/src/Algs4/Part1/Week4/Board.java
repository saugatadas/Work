import java.util.ArrayList;

public class Board {
    private int[][] boardTiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        boardTiles = new int[tiles.length][tiles[0].length];
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles[0].length; j++) {
                boardTiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(boardTiles.length+"\n");
        for (int i=0; i<boardTiles.length; i++) {
            for (int j=0; j<boardTiles[0].length; j++) {
                s.append(boardTiles[i][j]+" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return boardTiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        int v = 1;
        for (int i=0; i<boardTiles.length; i++) {
            for (int j=0; j<boardTiles[0].length; j++) {
                if (boardTiles[i][j] != v && boardTiles[i][j] !=0)
                    count++;
                v++;
            }
        }

        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        int v = 1;
        for (int i=0; i<boardTiles.length; i++) {
            for (int j=0; j<boardTiles[0].length; j++) {
                if (boardTiles[i][j] != v && boardTiles[i][j] !=0) {
                    int x = ((boardTiles[i][j]-1)/boardTiles.length);
                    int y = ((boardTiles[i][j]-1)%boardTiles.length);
                    count += (Math.abs(x-i) + Math.abs(y-j));
                }
                v++;
            }
        }

        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int v = 1;
        for (int i=0; i<boardTiles.length; i++) {
            for (int j=0; j<boardTiles[0].length; j++) {
                if (boardTiles[i][j] != v && boardTiles[i][j]!=0)
                    return false;
                v++;
            }
        }

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (y.getClass()!=this.getClass()) return false;

        Board b = (Board) y;
        if (boardTiles.length != b.boardTiles.length)
            return false;
        if (boardTiles[0].length != b.boardTiles[0].length)
            return false;

        for (int i=0; i<boardTiles.length; i++) {
            for (int j = 0; j < boardTiles[0].length; j++) {
                if (boardTiles[i][j] != b.boardTiles[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int p = -1, q = -1;
        ArrayList<Board> res = new ArrayList<Board>();

        for (int i=0; i<boardTiles.length; i++) {
            for (int j=0; j<boardTiles[0].length; j++) {
                if (boardTiles[i][j] == 0) {
                    p = i;
                    q = j;
                }
            }
        }

        if (p==-1 || q==-1)
            return res;

        if (p>0)
            res.add(exchange(p, q, p-1, q));
        if (q>0)
            res.add(exchange(p, q, p, q-1));
        if (p<(boardTiles.length-1))
            res.add(exchange(p, q, p+1, q));
        if (q<(boardTiles[0].length-1))
            res.add(exchange(p, q, p, q+1));

        return res;
    }


    private Board exchange(int p1, int q1, int p2, int q2) {
        Board b = new Board(this.boardTiles);
        int t = b.boardTiles[p1][q1];
        b.boardTiles[p1][q1] = b.boardTiles[p2][q2];
        b.boardTiles[p2][q2] = t;
        return b;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (boardTiles[0][0] != 0 && boardTiles[0][1] != 0)
            return exchange(0, 0, 0, 1);

        return exchange(1, 0, 1, 1);
    }

    // unit testing (not graded)
    /*
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
        System.out.println(board);

        System.out.println("Neighbors");
        for (Board b: board.neighbors())
            System.out.println(b);

        System.out.println("Twin");
        System.out.println(board.twin());

        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
    }
     */
}
