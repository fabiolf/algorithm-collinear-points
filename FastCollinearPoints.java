import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private LineSegment[] mSegments = null;
    
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new java.lang.IllegalArgumentException("null argument provided");

        Queue<Point[]> segQueue = new Queue<Point[]>();
        
        Point[] bkpPoints = new Point[points.length];
        for (int w = 0; w < points.length; w++) {
            bkpPoints[w] = points[w];
        }
        
        int j = 1;
        for (Point p : bkpPoints) {
            System.out.println("p: " + p.toString());
            Arrays.sort(points, p.slopeComparator);
            double slope = Double.NaN;
            int index = 0;
            for (int i = 1; i < points.length; i++) {
                System.out.println("points[i]: " + points[i].toString());
                double auxSlope = p.slopeTo(points[i]);
                if (auxSlope != slope) {
                    if (i - index < 3) {
                        index = i;
                        slope = auxSlope;
                    } else {
                        StdOut.println(p.toString() + " ---> " + points[i-1].toString());
                        // select all points that will be part of the line segment
                        // p+points[index] to points[i-1]
                        Point[] pointsOfASegment = new Point[index-i+1];
                        pointsOfASegment[0] = p;
                        for ()
                        // sort them (not by slope)
                        // iterate over queue to verify if it is a subsegment of an existing segment 
                        // (in which case we discard it) or if it is a super segment (in which case
                        // we replace the existing one 
                        segQueue.enqueue(new LineSegment(p, points[i-1]));
                        break;
                    }
                } 
            }
            j++;
        }
        if (segQueue.size() > 0) {
            mSegments = new LineSegment[segQueue.size()];
            int i = 0;
            for (LineSegment ls: segQueue) {
                mSegments[i] = ls;
                i++;
            }
        }
    }
    
    public int numberOfSegments() {
        // the number of line segments
        if (mSegments == null) return 0;
        return mSegments.length;
    }
    
    public LineSegment[] segments() {
        return mSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        System.out.println("teste2");
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        //Fabio
//        int i = 0;
        for (Point p : points) {
//            p.draw(i);
            p.draw();
//            i++;
        }
        StdDraw.show();
        

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        
    }

}
