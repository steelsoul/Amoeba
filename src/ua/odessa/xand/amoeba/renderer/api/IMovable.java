package ua.odessa.xand.amoeba.renderer.api;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 21.06.13
 * Time: 11:45
 */
public interface IMovable {
    void move(Point to);
    void stop(boolean forced);
    void setCollisionCheck(boolean state);
    boolean isCollisionChecked();
}
