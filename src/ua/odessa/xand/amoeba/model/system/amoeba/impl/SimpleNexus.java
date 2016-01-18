package ua.odessa.xand.amoeba.model.system.amoeba.impl;

import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseTransformer;
import ua.odessa.xand.mathStuff.MathHelper;

public class SimpleNexus implements IBaseNexus {
	private final static double ShapePrecision = 0.1;
	private IBaseTransformer mTransformer;
	private Line2D mBase;
	private Line2D mControl;
	
	public SimpleNexus(Point2D point1, Point2D point2, IBaseTransformer transformer) {
		mTransformer = transformer;
		mBase = new Line2D.Double(point1, point2);
		mControl = new Line2D.Double(point1, point2);
	}

	@Override
	public void transform() {
		mTransformer.doTransform();
	}

	@Override
	public boolean isPointHere(Point2D test) {
		Polygon perimeter = new Polygon();
		Vector<Line2D> shape = getShape();
		for (Line2D temp: shape) { 
			perimeter.addPoint((int)temp.getX1(), (int)temp.getY1());
			perimeter.addPoint((int)temp.getX2(), (int)temp.getY2());
		}
		return  (perimeter.contains(test));
	}

    @Override
    public Line2D getBase() {
        return mBase;
    }

    @Override
    public void setPosition(Point2D position) {
        // Shift control and base points according to the new position.
        mBase.setLine(mBase.getX1() + position.getX(), mBase.getY1() + position.getY(),
                mBase.getX2() + position.getX(), mBase.getY2() + position.getY());
        mControl.setLine(mControl.getX1() + position.getX(), mControl.getY1() + position.getY(),
                mControl.getX2() + position.getX(), mControl.getY2() + position.getY());
    }

    @Override
	public Point2D getPosition() {
		return new Point2D.Double(mBase.getX1(), mBase.getY1());
	}

	@Override
	public Line2D getControl() {
		return mControl;
	}
	
	@Override
	public void setFirstControl(Point2D control) {
		mControl.setLine(control.getX(), control.getY(), mControl.getX2(), mControl.getY2());
	}

	@Override
	public Vector<Line2D> getShape() {
		Vector<Line2D> shape = new Vector<Line2D>();
		Vector<Point2D> points = new Vector<Point2D>();

		double iteration;
		for (iteration = 0.; iteration < 1.0; iteration += ShapePrecision) {
			points.add(MathHelper.calculateBeizer(mBase.getP1(), mControl.getP1(), 
					mControl.getP2(), mBase.getP2(), iteration));
		}
		if (iteration - ShapePrecision < 1.0)
			points.add(mBase.getP2());
		
		Point2D p1 = new Point2D.Double(points.get(0).getX(), points.get(0).getY());
		Point2D p2 = new Point2D.Double();
		for (int i = 1; i < points.size(); i++) {
			p2.setLocation(points.get(i));
			shape.add(new Line2D.Double(p1, p2));
			p1.setLocation(p2);
		}
		
		return shape;
	}

    @Override
	public Point2D generateControl() {
		double distance = mBase.getP1().distance(mBase.getP2());
		double Ro = distance - distance/5;
		double height = distance / 4;
		double xK = Ro*(1 + mTransformer.getT()/distance);
		double yK = height*(1 + mTransformer.getT()/distance);
		double direction = (mBase.getX2() - mBase.getX1())*(mBase.getY1() - mBase.getY2());
		
		if (direction == 0)	{
			double newX = 0., newY = 0.;
			if (mBase.getX1() == mBase.getX2()) {
				newY = mBase.getY1() > mBase.getY2() ? mBase.getY1() - xK : mBase.getY1() + xK;
				newX = mBase.getY1() > mBase.getY2() ? mBase.getX1() - yK : mBase.getX1() + yK;
			}
			else if (mBase.getY1() == mBase.getY2()) {
				newY = mBase.getX1() > mBase.getX2() ? mBase.getY1() + yK : mBase.getY1() - yK;
				newX = mBase.getX1() > mBase.getX2() ? mBase.getX1() - xK : mBase.getX1() + xK;
			}
			mControl.setLine(mControl.getX1(), mControl.getY1(), newX, newY);
			return new Point2D.Double(2*mBase.getX2() - mControl.getX2(), 2*mBase.getY2() - mControl.getY2());
		}
		
		if (Math.signum(direction) > 0) 
		{
			if (mBase.getX1() > mBase.getX2() && mBase.getY1() < mBase.getY2())
				xK *= -1;
			if (mBase.getX1() < mBase.getX2() && mBase.getY1() > mBase.getY2())
				yK *= -1;
		}
		else
		{
			if (mBase.getX1() > mBase.getX2() && mBase.getY1() > mBase.getY2())
				xK *= -1;
			if (mBase.getX1() < mBase.getX2() && mBase.getY1() < mBase.getY2())
				yK *= -1;
		} 
		
		double angle = Math.atan((mBase.getY1() - mBase.getY2())/(mBase.getX2() - mBase.getX1()));
		Point2D rotated = MathHelper.rotate(new Point2D.Double(xK, yK), angle);
		mControl.setLine(mControl.getX1(), mControl.getY1(),
				rotated.getX() + mBase.getX1(), rotated.getY() + mBase.getY1());

		return new Point2D.Double(2*mBase.getX2() - mControl.getX2(), 2*mBase.getY2() - mControl.getY2());
	}

	@Override
	public void move(double dx, double dy) {
		mBase.setLine(mBase.getX1() + dx, mBase.getY1() + dy, mBase.getX2() + dx, mBase.getY2() + dy);
	}

}
