import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> m;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        Point[] pointsLocal = new Point[points.length];
        if (points == null ) {
            throw new IllegalArgumentException();
        }

        for (int i=0; i<points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            pointsLocal[i] = points[i];
        }

        m = new ArrayList<LineSegment>();
        Arrays.sort(pointsLocal);
        for (int i1=0; i1<pointsLocal.length; i1++) {
            if (i1 > 0 && pointsLocal[i1].compareTo(pointsLocal[i1-1])==0)
                throw new IllegalArgumentException();
            for (int i2=i1+1; i2<pointsLocal.length; i2++) {
                for (int i3=i2+1; i3<pointsLocal.length; i3++) {
                    if((pointsLocal[i1].slopeTo(pointsLocal[i2]) != pointsLocal[i1].slopeTo(pointsLocal[i3])))
                        continue;
                    for (int i4=i3+1; i4<pointsLocal.length; i4++) {
                        if((pointsLocal[i1].slopeTo(pointsLocal[i3]) == pointsLocal[i1].slopeTo(pointsLocal[i4]))) {
                            LineSegment l = new LineSegment(pointsLocal[i1], pointsLocal[i4]);
                            m.add(l);
                        }
                    }
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
