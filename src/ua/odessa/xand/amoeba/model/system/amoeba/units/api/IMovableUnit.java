package ua.odessa.xand.amoeba.model.system.amoeba.units.api;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseUnit;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:35
 */
public interface IMovableUnit extends IBaseUnit {
    void move(Point to);
    void stop(boolean forced);
    Point getDestination();
    // Collision Detection System
    void enableCDS(boolean state);
    boolean isCDSactive();

}
