import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Comparator;


public class KdTree {
    private static class Node {

        private final Point2D p;
        private Node left, right;
        private boolean direction;

        public Node(Point2D p, Node lt, Node rt, boolean d) {
            this.p = p;
            left = lt;
            right = rt;
            direction = d;
        }
    }

    private Node root;
    private int size;
    private double current_min;
    private Node current_node;

    public KdTree() {
        // construct an empty set of points
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        // is the set empty?
        return (root==null) ? true : false;
    }

    public int size() {
        // number of points in the set
        return size;
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p==null)
            throw new IllegalArgumentException();

        if (contains(p))
            return;

        root = insert(root, p, true);
        size++;
    }

    private Node insert(Node n, Point2D p, boolean direction) {

        if (n==null) {
            return new Node(p, null, null, direction);
        }

        Comparator<Point2D> comp = direction ? Point2D.X_ORDER : Point2D.Y_ORDER;

        if (comp.compare(n.p, p)>0) {
            n.left = insert(n.left, p, !direction);
        }
        else {
            n.right = insert(n.right, p, !direction);
        }

        return n;
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        if (p==null)
            throw new IllegalArgumentException();
        return contains(root, p);
    }

    private boolean contains(Node n, Point2D p) {

        if (n==null)
            return false;

        boolean direction = n.direction;
        Comparator<Point2D> comp = direction ? Point2D.X_ORDER : Point2D.Y_ORDER;

        if (n.p.equals(p))
            return true;

        if (comp.compare(n.p, p) > 0) {
            return contains(n.left, p);
        }
        else {
            return contains(n.right, p);
        }
    }

    public void draw() {
        // draw all points to standard draw
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node n, RectHV rect) {
        if (n == null)
            return;

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        n.p.draw();

        StdDraw.setPenRadius();
        if (n.direction) {
            //vertical
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.p.x(), rect.ymin(), n.p.x(), rect.ymax());

            draw(n.left, new RectHV(rect.xmin(), rect.ymin(), n.p.x(), rect.ymax()));
            draw(n.right, new RectHV(n.p.x(), rect.ymin(), rect.xmax(), rect.ymax()));
        }
        else {
            //horizontal
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.p.x(), rect.ymin(), n.p.x(), rect.ymax());

            draw(n.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.p.y()));
            draw(n.right, new RectHV(rect.xmin(), n.p.y(), rect.xmax(), rect.ymax()));
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException();

        ArrayList<Point2D> res = new ArrayList<>();
        range(root, rect, res);
        return res;
    }

    private void range(Node n, RectHV rect, ArrayList<Point2D> res) {
        if (n==null)
            return;

        if (rect.contains(n.p))
            res.add(n.p);

        if (n.direction) {
            //vertical
            if (n.p.x() >= rect.xmin() && n.p.x() <= rect.xmax()) {
                range(n.left, rect, res);
                range(n.right, rect, res);
            }
            else if(n.p.x() <= rect.xmin()) {
                range(n.right, rect, res);
            }
            else {
                range(n.left, rect, res);
            }
        }
        else {
            //horizontal
            if (n.p.y() >= rect.ymin() && n.p.y() <= rect.ymax()) {
                range(n.left, rect, res);
                range(n.right, rect, res);
            }
            else if(n.p.y() <= rect.ymin()) {
                range(n.right, rect, res);
            }
            else {
                range(n.left, rect, res);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        current_min = Double.POSITIVE_INFINITY;
        current_node = null;
        nearest(root, p, new RectHV(0, 0, 1, 1));

        if (current_node == null)
            return null;

        return current_node.p;
    }

    private void nearest(Node n, Point2D p, RectHV rect) {
        if (n==null)
            return;

        double distance = n.p.distanceSquaredTo(p);
        if (distance < current_min) {
            current_min = distance;
            current_node = n;
        }

        if (rect.distanceSquaredTo(p) > current_min)
            return;

        RectHV left_rect, right_rect;

        // search in both sides
        if (n.direction) {
            left_rect = new RectHV(rect.xmin(), rect.ymin(), n.p.x(), rect.ymax());
            right_rect = new RectHV(n.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
        }
        else {
            left_rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.p.y());
            right_rect = new RectHV(rect.xmin(), n.p.y(), rect.xmax(), rect.ymax());
        }

        nearest(n.right, p, right_rect);
        nearest(n.left, p, left_rect);
    }

    public static void main(String[] args)   {
        // unit testing of the methods (optional)
    }
}
