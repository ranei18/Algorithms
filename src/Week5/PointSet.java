import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
/*
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
*/
public class PointSET {
    private TreeSet tree;

    public PointSET() {
        tree = new TreeSet();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    public void draw() {
        Iterator<Point2D> iterator = tree.iterator();
        while (iterator.hasNext()) {
            iterator.next().draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> iterable = new ArrayList();
        Iterator<Point2D> iterator = tree.iterator();

        while (iterator.hasNext()) {
            Point2D next = iterator.next();
            if (rect.contains(next)) {
                iterable.add(next);
            }
        }
        return iterable;
    }

    public Point2D nearest(Point2D p) {
        double distance = Double.MAX_VALUE;
        Point2D nearest = null;
        Iterator<Point2D> iterator = tree.iterator();

        while (iterator.hasNext()) {
            Point2D next = iterator.next();
            if (next.distanceTo(p) < distance) {
                distance = next.distanceTo(p);
                nearest = next;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        StdDraw.setPenRadius(0.01);
        PointSET set = new PointSET();
        set.insert(new Point2D (0.1,0.1));
        set.insert(new Point2D(0.5,0.5));
        set.insert(new Point2D(0.7,0.7));
        set.draw();
        RectHV rect = new RectHV(0.1,0.1,0.5,0.5);

        for (Point2D item : set.range(rect)) {
            System.out.println(item);
        }
    }
}

