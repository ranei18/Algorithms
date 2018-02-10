import java.util.ArrayList;
/*
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
*/
public class KdTree {
    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if(p == null) throw new IllegalArgumentException();
        if(!contains(p)) {
            root = insertNode(p, root, true);
        }
    }

    private Node insertNode(Point2D p, Node node, boolean isVertical) {
        if(node == null) {
            size++;
            return new Node(p,isVertical);
        }
        else {
            if ((node.isVertical && p.x() <= node.getPoint().x()) || (!node.isVertical && p.y() <= node.getPoint().y())) {
                node.left = insertNode(p, node.left, !node.isVertical);
            }
            else {
                node.right = insertNode(p, node.right, !node.isVertical);
            }
            return node;
        }
    }

    public boolean contains(Point2D p) {
        if(p == null) throw new IllegalArgumentException();
        Node node = root;
        while (node != null) {
            if (node.getPoint().x() == p.x() && node.getPoint().y() == p.y()) {
                return true;
            }
            else if ((node.isVertical && p.x() <= node.getPoint().x()) || (!node.isVertical && p.y() <= node.getPoint().y())) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return false;
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.getPoint().draw();
            if (node.isVertical) {
                StdDraw.setPenColor(StdDraw.RED);
                //draw horizontal line
                node.getPoint().draw();
            }
            else {
                StdDraw.setPenColor(StdDraw.BLUE);
                //draw vertical line
                node.getPoint().draw();
            }
            draw(node.left);
            draw(node.right);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> iterable = new ArrayList();
        range(rect, root, iterable);
        return iterable;
    }

    private void range(RectHV rect, Node node, ArrayList<Point2D> iterable) {
        if (node != null) {
            if (rect.contains(node.getPoint())) {
                iterable.add(node.getPoint());
            }
            if ((node.isVertical && rect.xmin() <= node.getPoint().x()) || (!node.isVertical && rect.ymin() <= node.getPoint().y())) {
                range(rect, node.left, iterable);
            }
            if ((node.isVertical && rect.xmax() > node.getPoint().x()) || (!node.isVertical && rect.ymax() > node.getPoint().y())) {
                range(rect, node.right, iterable);
            }
        }
    }
    public Point2D nearest(Point2D p) {
        if(p == null) throw new IllegalArgumentException();
        double distance = Double.MAX_VALUE;
        Point2D nearest = null;
        return nearestHelp(p, distance, nearest, root);
    }

    private Point2D nearestHelp(Point2D p,double d, Point2D n, Node node) {

        if (node != null) {
            //System.out.println("node " + node.getPoint());
            // System.out.println("distance " + node.getPoint().distanceSquaredTo(p));
            if (node.getPoint().distanceSquaredTo(p) < d) {
                d = node.getPoint().distanceTo(p);
                n = node.getPoint();
            }
            Point2D left = nearestHelp(p, d, n, node.left);
            Point2D right = nearestHelp(p, d, n, node.right);
            if(left.distanceSquaredTo(p) < right.distanceSquaredTo(p) && left.distanceSquaredTo(p) < d){//node.getPoint().distanceTo(p)) {
                return left;
            }
            if(right.distanceSquaredTo(p) < left.distanceSquaredTo(p) && right.distanceSquaredTo(p) < d){//node.getPoint().distanceTo(p)) {
                return right;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        StdDraw.setPenRadius(0.01);
        KdTree set = new KdTree();
        set.insert(new Point2D (0.7, 0.2));
        set.insert(new Point2D (0.5, 0.4));
        set.insert(new Point2D (0.2, 0.3));
        set.insert(new Point2D (0.4, 0.7));
        set.insert(new Point2D (0.9, 0.6));
        set.draw();
        //System.out.println(set.contains(new Point2D(0.1,0.1)));
        //System.out.println("nearest");
        System.out.println(set.nearest(new Point2D(0.004, 0.648)));
        StdDraw.setPenColor(StdDraw.GREEN);
        new Point2D(0.004, 0.648).draw();
        StdDraw.setPenColor(StdDraw.BLACK);
        new Point2D(0.5, 0.4).draw();
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        new Point2D(0.4, 0.7).draw();
    }

    private class Node implements Comparable<Node> {
        private final Point2D point;
        private final boolean isVertical;
        private Node left;
        private Node right;

        private Node(Point2D p, boolean v) {
            this.point = p;
            this.isVertical = v;
            this.left = null;
            this.right = null;
        }

        public int compareTo(Node b) {
            return point.compareTo(b.getPoint());
        }

        public Point2D getPoint() {
            return point;
        }

        public boolean isVertical() {
            return isVertical;
        }
    }

}
