package ua.odessa.xand.amoeba.model.system.amoeba.api;

import ua.odessa.xand.amoeba.model.system.IBaseObject;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:03
 */


public interface IBaseNexus extends IBaseObject {
    void transform();
    boolean isPointHere(Point2D test);
    Line2D getBase();
    Line2D getControl();
    Point2D generateControl();
    void setFirstControl(Point2D control);
    Vector<Line2D> getShape();
    void move(double dx, double dy);
}
