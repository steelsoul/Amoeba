package ua.odessa.xand.amoeba.model.system.amoeba.impl;

import java.awt.Point;
import java.awt.geom.Point2D;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseMotion;
import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseUnit;
import ua.odessa.xand.mathStuff.MathHelper;

public class StraightMotion implements IBaseMotion {
	private static final int DefaultProcessTimeoutMs = 50;
    private static final double DefaultSpeed = 1.2;
	private Point2D mTo;
	private double mSpeed;
	private boolean mIsRunning;
	private Thread mThread;
	private IBaseUnit mUnit;
	private double mDistance;
	private Point2D mCenter;
    private boolean mCDSEnabled;

	public StraightMotion() {
		mSpeed = DefaultSpeed;
		mTo = new Point2D.Double();
		mIsRunning = false;
		mThread = null;
		mDistance = 0;
		mCenter = null;
        mCDSEnabled = false;
    }

    @Override
    public void assignUnit(IBaseUnit unit) {
        mUnit = unit;
    }

    @Override
	public void stop(boolean isForced) {
		if (mThread == null) return;
		synchronized (this) {
			mIsRunning = false;
			try {
				mThread.join(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!isForced)
                while (mThread.isAlive());
		}
		mThread = null;
		mDistance = 0;
		mCenter = null;
	}
	
	@Override
	public void start(Point to) {
		mTo.setLocation(to.getX(), to.getY());
        mCenter =  MathHelper.getCenter(mUnit.getBounds());
		mDistance = mCenter.distance(mTo);		
		if (!mIsRunning)
		{
			mThread = new Thread(this);
			mThread.start();
			while (!mThread.isAlive());
		}
	}
	
	@Override
	public void run() {
		mIsRunning = true;
		
		Point2D center = MathHelper.getCenter(mUnit.getBounds());
		double distance = 0.0;
					
		while (mIsRunning) {
            {
                // TODO: check collision

            }
			if (mSpeed <= 0.) break;
			if (mTo != null) distance = center.distance(mTo);
			
			if (distance <= MathHelper.AxisLimit*mSpeed || !mIsRunning) break;
			
			if (mTo != null && mCenter != null && mDistance != 0.0 && mUnit != null) {
				double dY = mSpeed * (mTo.getY() - mCenter.getY()) / mDistance;
				double dX = mSpeed * (mTo.getX() - mCenter.getX()) / mDistance;
				mUnit.move(dX, dY);
                center = MathHelper.getCenter(mUnit.getBounds());
            }
			else break;

			synchronized (this) {
				try {
					wait(DefaultProcessTimeoutMs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		mIsRunning = false;
	}
	
	@Override 
	public Point getDestination() {
		if (mIsRunning)
			return new Point((int)mTo.getX(), (int)mTo.getY());
		else
			return null;
	}

    @Override
    public void enableCDS(boolean state) {
        mCDSEnabled = state;
    }

    @Override
    public boolean isCDSOn() {
        return mCDSEnabled;
    }
}
