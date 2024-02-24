import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double[] trial;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        trial = new double[trials];

        for (int i=0; i<trials; i++) {
            Percolation p = new Percolation(n);

            while(!p.percolates()) {
                int r = StdRandom.uniformInt(1, n+1);
                int c = StdRandom.uniformInt(1, n+1);
                p.open(r, c);
            }

            trial[i] = p.numberOfOpenSites()/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trial);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trial);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-((1.96 * stddev())/Math.sqrt(trial.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return mean()+((1.96 * stddev())/Math.sqrt(trial.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int grid = Integer.parseInt(args[0]);       //Enter an n and test times T
        int trial = Integer.parseInt(args[0]);
        PercolationStats m = new PercolationStats(grid, trial);
        StdOut.println("mean = " + m.mean());
        StdOut.println("stddev = " + m.stddev());
        StdOut.println("95% confidence interval = [" + m.confidenceLo() + ", "+ m.confidenceHi() + "]");
    }
}