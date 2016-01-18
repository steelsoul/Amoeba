package ua.odessa.xand.amoeba.model.system.amoeba.units.impl;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseUnit;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 22.06.13
 * Time: 19:56
 *
 * Simple amoeba unit.
 * Only able to generate bounds and perimeter.
 * Without transformation.
 */

public class SimpleUnit implements IBaseUnit {
    protected List<IBaseNexus> mNexusList;


    public SimpleUnit() {
        mNexusList = new ArrayList<IBaseNexus>();
    }

    @Override
    public void push_back(IBaseNexus base) {
        mNexusList.add(base);
    }

    @Override
    public void move(double dx, double dy) {
        for (IBaseNexus bn: mNexusList)
            bn.move(dx, dy);
    }

    @Override
    public Vector<Line2D> getShape() {
        Vector<Line2D> shape = new Vector<Line2D>();
        for (IBaseNexus bn: mNexusList)
            shape.addAll(bn.getShape());
        return shape;
    }

    @Override
    public Vector<Line2D> getControls() {
        Vector<Line2D> controls = new Vector<Line2D>();
        for (IBaseNexus bn: mNexusList) {
            controls.add(bn.getControl());
        }
        return controls;
    }

    @Override
    public Polygon getBase() {
        Polygon bounds = new Polygon();
        for (IBaseNexus bn: mNexusList) {
            bounds.addPoint((int)bn.getBase().getX1(), (int)bn.getBase().getY1());
            bounds.addPoint((int)bn.getBase().getX2(), (int)bn.getBase().getY2());
        }
        return bounds;
    }

    @Override
    public Vector<Point2D> getBounds() {
        Vector<Point2D> bounds = new Vector<Point2D>();
        for (IBaseNexus bn: mNexusList) {
            Point2D point = bn.getBase().getP1();
            bounds.add(point);
        }
        return bounds;
    }

    @Override
    public void setPosition(Point2D position) {
        // shift generated amoeba contour with position values.
        for (IBaseNexus bn: mNexusList)
            bn.setPosition(position);
    }

    @Override
    public Point getPosition() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
