import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Percolation {

    private boolean[][] grid;
    private int openSites;
    private WeightedQuickUnionUF gridSet;
    private WeightedQuickUnionUF gridSet2;

    private int top;
    private int bottom;
    private int size;

    private int convertAddress(int row, int col) {
        return row*grid.length+col;
    }

    private void validate(int row, int col) {
        if (row<1 || col<1 || row>size || col>size)
            throw new IllegalArgumentException();
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n<=0) throw new IllegalArgumentException();
        grid = new boolean[n][n];
        size = n;

        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }

        gridSet = new WeightedQuickUnionUF(n*n+2); //with both top and bottom
        gridSet2 = new WeightedQuickUnionUF(n*n+1); //with only top
        openSites = 0;

        top = n*n;
        bottom = n*n+1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row1, int col1) {
        validate(row1, col1);

        int row = row1-1;
        int col = col1-1;

        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;

            if (row + 1 < grid.length && grid[row + 1][col]) {
                gridSet.union(convertAddress(row, col), convertAddress(row + 1, col));
                gridSet2.union(convertAddress(row, col), convertAddress(row + 1, col));
            }

            if (col + 1 < grid.length && grid[row][col + 1]) {
                gridSet.union(convertAddress(row, col), convertAddress(row, col + 1));
                gridSet2.union(convertAddress(row, col), convertAddress(row, col + 1));
            }

            if (row - 1 >= 0 && grid[row - 1][col]) {
                gridSet.union(convertAddress(row, col), convertAddress(row - 1, col));
                gridSet2.union(convertAddress(row, col), convertAddress(row - 1, col));
            }

            if (col - 1 >= 0 && grid[row][col - 1]) {
                gridSet.union(convertAddress(row, col), convertAddress(row, col - 1));
                gridSet2.union(convertAddress(row, col), convertAddress(row, col - 1));
            }

            if (row == 0) {
                gridSet.union(convertAddress(row, col), top);
                gridSet2.union(convertAddress(row, col), top);
            }

            if (row == grid.length - 1) {
                gridSet.union(convertAddress(row, col), bottom);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        row = row - 1;
        col = col - 1;
        int m1 = gridSet2.find(convertAddress(row, col));
        int m2 = gridSet2.find(top);
        if (m1==m2)
            return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        int p = gridSet.find(top);
        int q = gridSet.find(bottom);
        if (p==q)
            return true;
        else
                        return false;
    }

    // test client (optional)
    /*
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(size);
        int count = args.length-1;
        int index = 1;

        while(count>0) {
            int r = Integer.parseInt(args[index]);
            int c = Integer.parseInt(args[index+1]);
            percolation.open(r, c);
            count-=2;
            index+=2;
        }

        if (percolation.percolates()) {
            StdOut.print("It percolates");
        }
        else {
            StdOut.print("It does not percolates");
        }
    }
     */
}