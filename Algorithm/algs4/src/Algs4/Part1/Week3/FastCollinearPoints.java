import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> m;

    public FastCollinearPoints(Point[] points) {
        m = new ArrayList<LineSegment>();
        if (points == null)
            throw new IllegalArgumentException();

        Point[] points2 = new Point[points.length];
        for (int i=0; i<points.length; i++) {
            if (points[i]==null)
                throw new IllegalArgumentException();
            points2[i] = points[i];
        }

        Arrays.sort(points2);
        for (int i=1; i<points2.length; i++) {
            if (points2[i].compareTo(points2[i-1])==0)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < points2.length; i++) {
            Point p = points2[i];
            Point[] points2_2 = new Point[points2.length];

            for (int j=0; j<points2.length; j++)
                points2_2[j] = points2[j];

            Arrays.sort(points2_2, p.slopeOrder());
            double currentSlope = 0.0;
            int left = 0;
            int right = 0;
            for (int i2=0; i2<points2_2.length; i2++) {
                double slope2 = p.slopeTo(points2_2[i2]);
                if (currentSlope == slope2) {
                    right++;
                }
                else {
                    if ((right-left) >= 2) {
                        LineSegment l = new LineSegment(points2_2[left], points2_2[right]);
                        m.add(l);
                    }
                    left=i2;
                    right=i2;
                    currentSlope = slope2;
                }
            }
        }
    }

    public int numberOfSegments() {
        return m.size();
    }

    public LineSegment[] segments() {
        LineSegment[] l = new LineSegment[m.size()];
        for (int i=0; i<m.size(); i++) {
            l[i] = m.get(i);
        }
        return l;
    }
}
