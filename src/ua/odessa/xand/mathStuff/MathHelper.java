package ua.odessa.xand.mathStuff;

import java.awt.geom.Point2D;
import java.util.Vector;

import Jama.Matrix;

public final class MathHelper {
	public static final double AxisLimit = 0.5;
	
	/**
	 *  @param p1 - first base point
	 *  @param p2 - first control point
	 *  @param p3 - second control point
	 *  @param p4 - second base point
	 *  @param i - iteration counter between 0 and 1 
	 *  @return point on the Beizer curve
	 * */
	public static Point2D calculateBeizer(Point2D p1, Point2D p2, Point2D p3, Point2D p4, double i) {
		Point2D tpoint = new Point2D.Double();

		double c = 3*(p2.getX() - p1.getX());
		double b = 3*(p3.getX() - p2.getX()) - c;
		double a = p4.getX() - p1.getX() - b - c;
		double xR = a*i*i*i + b*i*i + c*i + p1.getX();

		c = 3*(p2.getY() - p1.getY());
		b = 3*(p3.getY() - p2.getY()) - c;
		a = p4.getY() - p1.getY() - b - c;
		double yR = a*i*i*i + b*i*i + c*i + p1.getY();
		
		tpoint.setLocation(xR, yR);

		return tpoint;
	}
	
	public static Point2D rotate(Point2D which, double angle) {
		Matrix point = new Matrix(1, 2);
		point.set(0, 0, which.getX());
		point.set(0, 1, which.getY());
		Matrix rotor = new Matrix(2, 2);
		rotor.set(0,  0,  Math.cos(angle));
		rotor.set(0,  1, -Math.sin(angle));
		rotor.set(1,  0,  Math.sin(angle));
		rotor.set(1,  1,  Math.cos(angle));
        //Matrix result = new Matrix(1, 2);
		Matrix result = point.times(rotor);
		return new Point2D.Double(result.get(0,  0), result.get(0, 1));		
	}
		
//	public static Point2D calculateIntersection(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
//		if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) return null;
//		if (p3.getX() == p4.getX() && p3.getY() == p4.getY()) return null;
//
//		Matrix givenEq = new Matrix(2, 2);
//
//		givenEq.set(0, 0, p1.getY() - p2.getY());
//		givenEq.set(0, 1, p2.getX() - p1.getX());
//		givenEq.set(1, 0, p3.getY() - p4.getY());
//		givenEq.set(1, 1, p4.getX() - p3.getX());
//
//		if (givenEq.det() == 0)
//			return null;
//
//		Matrix koeff = new Matrix(2, 2);
//
//		koeff.set(0, 0, p2.getX()*p1.getY() - p1.getX()*p2.getY());
//		koeff.set(1, 0, p4.getX()*p3.getY() - p3.getX()*p4.getY());
//		Matrix solution = givenEq.solve(koeff);
//		return new Point2D.Double(solution.get(0, 0), solution.get(1, 0));
//	}
	
//	public static Point2D getSegmentsIntersection(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
//		Point2D sol = calculateIntersection(p1, p2, p3, p4);
//		if (sol == null) return sol;
//		if (isPointOnLine(p1, p2, sol) && isPointOnLine(p3, p4, sol))
//			return sol;
//		return null;
//
//	}
	
//	public static Point2D getElementsIntersection(Vector<Point2D> e1, Vector<Point2D> e2)
//	{
//		Point2D intersection;
//		for (int i = 0; i < e1.size() - 1; i++) {
//			for (int j = 0; j < e2.size() - 1; j++ ) {
//				intersection = getSegmentsIntersection(e1.get(i), e1.get(i+1), e2.get(j), e2.get(j+1));
//				if (intersection != null) return intersection;
//			}
//		}
//		return null;
//	}
//
//	public static boolean isPointOnLine(Point2D p1, Point2D p2, Point2D p) {
//		double minX = p1.getX() > p2.getX() ? p2.getX() : p1.getX();
//		double maxX = p1.getX() > p2.getX() ? p1.getX() : p2.getX();
//        return minX != maxX
//                && ((p.getX() >= minX + AxisLimit && p.getX() <= maxX + AxisLimit)
//                ||  (p.getX() >= minX - AxisLimit && p.getX() <= maxX - AxisLimit));
//	}
	
	public static Point2D getCenter(Vector<Point2D> basePoints) {
		double xc = 0., yc = 0.;
		for (Point2D p: basePoints) {
			xc += p.getX(); yc += p.getY();
		}
		xc /= basePoints.size(); yc /= basePoints.size();
			
		return new Point2D.Double(xc, yc);
	}
	
}
