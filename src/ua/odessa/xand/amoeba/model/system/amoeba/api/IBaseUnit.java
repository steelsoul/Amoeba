package ua.odessa.xand.amoeba.model.system.amoeba.api;

import ua.odessa.xand.amoeba.model.system.IBaseObject;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:00
 */

public interface IBaseUnit extends IBaseObject {
    void push_back(IBaseNexus baseElement);
    void move(double dx, double dy);
    Vector<Line2D> getShape();
    Vector<Line2D> getControls();
    Polygon getBase();
    Vector<Point2D> getBounds();
}
