import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;


public class SeamCarver {
    // create a seam carver object based on the given picture
    private Picture p;
    private double[][] energy;
    private int[][] edgeTo;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();

        width = picture.width();
        height = picture.height();
        p = new Picture(width, height);
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                p.set(i, j, picture.get(i, j));
            }
        }

        energy = new double[width][height];
        edgeTo = new int[width][height];
    }

    // current picture
    public Picture picture() {
        Picture q = new Picture(width, height);
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                q.set(i, j, p.get(i, j));
            }
        }
        return q;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x<0 || x>=width || y<0 || y>= height)
            throw new IllegalArgumentException();

        if (x==0 || x==width-1 || y==0 || y==height-1)
            return 1000.0;

        double redx = Math.pow(p.get(x-1, y).getRed() - p.get(x+1, y).getRed(), 2);
        double greenx = Math.pow(p.get(x-1, y).getGreen() - p.get(x+1, y).getGreen(), 2);
        double bluex = Math.pow(p.get(x-1, y).getBlue() - p.get(x+1, y).getBlue(), 2);

        double redy = Math.pow(p.get(x, y-1).getRed() - p.get(x, y+1).getRed(), 2);
        double greeny = Math.pow(p.get(x, y-1).getGreen() - p.get(x, y+1).getGreen(), 2);
        double bluey = Math.pow(p.get(x, y-1).getBlue() - p.get(x, y+1).getBlue(), 2);

        double diffx = redx + greenx + bluex;
        double diffy = redy + greeny + bluey;

        return Math.sqrt(diffx+diffy);
    }

    // x is width direction. y is height direction
    private void relax(int x1, int y1, int x2, int y2, boolean markWidth) {
        if (energy[x2][y2] > (energy[x1][y1] + energy(x2, y2))) {
            energy[x2][y2] = energy[x1][y1] + energy(x2, y2);
            if (markWidth)
                edgeTo[x2][y2] = x1;
            else
                edgeTo[x2][y2] = y1;
        }
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                if (i==0)
                    energy[i][j] = 1000.0; //at width 0, initialize
                else
                    energy[i][j] = Double.POSITIVE_INFINITY;
                edgeTo[i][j] = -1;
            }
        }

        // relax right side
        for (int i=0; i<width-1; i++) {
            for (int j=0; j<height; j++) {
                if (j>0) {
                    relax(i, j, i+1, j-1, false);
                }
                relax(i, j, i+1, j, false);
                if (j<height-1) {
                    relax(i, j, i+1, j+1, false);
                }
            }
        }

        double minEnergy = Double.MAX_VALUE;
        int mini = -1;

        for (int i=0; i<height; i++) {
            if (energy[width-1][i] < minEnergy) {
                minEnergy = energy[width-1][i];
                mini = i;
            }
        }

        ArrayList<Integer> r = new ArrayList<>();
        for (int i=width-1; i>=0; i--) {
            r.add(mini);
            int k = edgeTo[i][mini];
            mini = k;
        }

        int[] res = new int[r.size()];
        for (int i=r.size()-1, j=0; i>=0; i--,j++)
            res[j] = r.get(i);

        return res;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                if (j==0)
                    energy[i][j] = 1000.0; //at height 0, initialize
                else
                    energy[i][j] = Double.POSITIVE_INFINITY;
                edgeTo[i][j] = -1;
            }
        }

        // relax downwards
        for (int i=0; i<height-1; i++) {
            for (int j=0; j<width; j++) {
                if (j>0) {
                    relax(j, i, j-1, i+1, true);
                }
                relax(j, i, j, i+1, true);
                if (j<width-1) {
                    relax(j, i, j+1, i+1, true);
                }
            }
        }

        double minEnergy = Double.MAX_VALUE;
        int mini = -1;

        for (int i=0; i<width; i++) {
            if (energy[i][height-1] < minEnergy) {
                minEnergy = energy[i][height-1];
                mini = i;
            }
        }

        ArrayList<Integer> r = new ArrayList<>();

        for (int i=height-1; i>=0; i--) {
            r.add(mini);
            int k = edgeTo[mini][i];
            mini = k;
        }

        int[] res = new int[r.size()];
        for (int i=r.size()-1, j=0; i>=0; i--,j++)
            res[j] = r.get(i);

        return res;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new IllegalArgumentException();

        if (seam.length != width)
            throw new IllegalArgumentException();

        for (int i=0; i<seam.length-1; i++) {
            if (Math.abs(seam[i]-seam[i+1])>1)
                throw new IllegalArgumentException();
        }

        Picture r = new Picture(width, height-1);

        for (int j=0; j<width; j++) {
            for (int i=0; i<seam[j]; i++) {
                r.set(j, i, p.get(j, i));
            }
            for (int i=seam[j]; i<height-1; i++) {
                r.set(j, i, p.get(j, i+1));
            }
        }
        this.p = r;
        width = r.width();
        height = r.height();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new IllegalArgumentException();

        if (seam.length != height)
            throw new IllegalArgumentException();

        for (int i=0; i<seam.length-1; i++) {
            if (Math.abs(seam[i]-seam[i+1])>1)
                throw new IllegalArgumentException();
        }

        Picture r = new Picture(width-1, height);

        for (int i=0; i<height; i++) {
            for (int j=0; j<seam[i]; j++) {
                r.set(j, i, p.get(j, i));
            }
            for (int j=seam[i]; j<width-1; j++) {
                r.set(j, i, p.get(j+1, i));
            }
        }
        this.p = r;
        width = r.width();
        height = r.height();
    }

    //  unit testing (optional)
    public static void main(String[] args) {
    }
}
