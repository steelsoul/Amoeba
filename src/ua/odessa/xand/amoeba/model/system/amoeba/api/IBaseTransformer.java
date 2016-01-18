package ua.odessa.xand.amoeba.model.system.amoeba.api;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:06
 */


public interface IBaseTransformer {
    void doTransform();
    double getT();
    void negateDirection();
}
