package ua.odessa.xand.amoeba.model.system.amoeba.api;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:12
 */

public interface IBaseMotion extends Runnable {
    void assignUnit(IBaseUnit unit);
    void stop(boolean isForced);
    void start(Point to);
    Point getDestination();
    // Collision Detection System
    void enableCDS(boolean state);
    boolean isCDSOn();
}
