import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    private class order implements Comparator<Point> {
        Point P;
        order(Point x) {
            P = x;
        }

        public int compare(Point p, Point q) {
            double m1 = P.slopeTo(p);
            double m2 = P.slopeTo(q);
            return Double.compare(m1, m2);
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public   void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    public   void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        if (this.y==that.y && this.x==that.x)
            return 0;

        if ((this.y < that.y) || (this.y==that.y && this.x<that.x))
            return -1;
        else
            return 1;
    }

    public double slopeTo(Point that) {
        if (that == null ) {
            throw new NullPointerException();
        }
        if (this.y == that.y && that.x == this.x)
            return Double.NEGATIVE_INFINITY;
        if (that.x == this.x)
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y)
            return 0;
        return (double) (this.y - that.y) / (this.x - that.x);
    }

    public Comparator<Point> slopeOrder() {
        return new order(this);
    }


    public static void main(String[] args) {
        /* YOUR CODE HERE */
        /*
        Point p = new Point(169, 196);
        Point q = new Point(253, 196);
        System.out.println(p.slopeTo(q));

         */
    }
}
