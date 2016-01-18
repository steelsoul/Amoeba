package ua.odessa.xand.amoeba.model.system.amoeba.impl;

import java.util.Random;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseTransformer;

public class SimpleTransformer implements IBaseTransformer {
    private final static int DEFAULT_RANGE = 50;
	private int mDirection;
	private int mRateRange;
	private double mT;
	private int mMaxRange;
	private Random mRnd;
	
	public SimpleTransformer() {
		mDirection = 1;
		mMaxRange = DEFAULT_RANGE;
		mRnd = new Random();
		regenerate();
	}
	
	public SimpleTransformer(int radius) {
		mDirection = 1;
		mMaxRange = (int)(radius / 1.2);
		mRnd = new Random();
		regenerate();
	}
	
	@Override
	public void doTransform() {
		mT += mDirection;
		if (mT > mRateRange) 
		{
			mDirection = -1;
		}
		else if (mT < -mRateRange)
		{
			mDirection = 1;
			regenerate();
		}
	}

	@Override
	public double getT() {
		return mT;
	}

	@Override
	public void negateDirection() {
		mDirection = -1;
	}

	private void regenerate() {
		mRateRange = mRnd.nextInt(mMaxRange/3) + mMaxRange/9;
	}
}
