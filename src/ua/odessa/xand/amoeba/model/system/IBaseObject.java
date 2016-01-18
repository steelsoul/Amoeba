package ua.odessa.xand.amoeba.model.system;

import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 24.06.13
 * Time: 14:24
 */

public interface IBaseObject {
    void setPosition(Point2D position);
    Point2D getPosition();
}
