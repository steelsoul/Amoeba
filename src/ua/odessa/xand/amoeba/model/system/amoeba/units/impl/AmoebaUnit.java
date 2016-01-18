package ua.odessa.xand.amoeba.model.system.amoeba.units.impl;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.units.api.IMovableUnit;
import ua.odessa.xand.amoeba.model.system.amoeba.units.api.ITransformableUnit;

public class AmoebaUnit extends SimpleUnit implements ITransformableUnit, IMovableUnit {
	private MovableUnit movableUnit;
	
	public AmoebaUnit(MovableUnit movableUnit) {
		this.movableUnit = movableUnit;
	}
	
	@Override
	public void move(double dx, double dy) {
		movableUnit.move(dx, dy);
	}

	@Override
	public Vector<Line2D> getShape() {
		return movableUnit.getShape();
	}

	@Override
	public Vector<Line2D> getControls() {
		// TODO Auto-generated method stub
		return movableUnit.getControls();
	}

	@Override
	public Polygon getBase() {
		// TODO Auto-generated method stub
		return movableUnit.getBase();
	}

	@Override
	public Vector<Point2D> getBounds() {
		// TODO Auto-generated method stub
		return movableUnit.getBounds();
	}

	@Override
	public void setPosition(Point2D position) {
		// TODO Auto-generated method stub
		movableUnit.setPosition(position);
	}

	@Override
	public void move(Point to) {
		// TODO Auto-generated method stub
		movableUnit.move(to);
	}

	@Override
	public void stop(boolean forced) {
		// TODO Auto-generated method stub
		movableUnit.stop(forced);
	}

	@Override
	public Point getDestination() {
		// TODO Auto-generated method stub
		return movableUnit.getDestination();
	}

	@Override
	public void enableCDS(boolean state) {
		// TODO Auto-generated method stub
		movableUnit.enableCDS(state);
	}

	@Override
	public boolean isCDSactive() {
		// TODO Auto-generated method stub
		return movableUnit.isCDSactive();
	}

	@Override
	public void push_back(IBaseNexus baseElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform() {
		movableUnit.transform();
//        // recalculate control points according to the transformation
//        for (int i = 0; i < mNexusList.size(); i++) {
//            int j = (i < mNexusList.size() - 1) ? i + 1 : 0;
//            Point2D first = mNexusList.get(i).generateControl();
//            mNexusList.get(j).setFirstControl(first);
//        }
//
//        for (IBaseNexus bn: mNexusList)
//            bn.transform();
	}

}
