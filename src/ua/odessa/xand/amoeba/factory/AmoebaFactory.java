package ua.odessa.xand.amoeba.factory;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseTransformer;
import ua.odessa.xand.amoeba.model.system.amoeba.units.impl.MovableUnit;
import ua.odessa.xand.amoeba.model.system.amoeba.impl.SimpleNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.impl.SimpleTransformer;
import ua.odessa.xand.amoeba.model.system.amoeba.impl.StraightMotion;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:41
 */

public final class AmoebaFactory {
    public static MovableUnit createMovableAmoeba(int radius, int nSides, Point2D location) {

        if (nSides < 3) nSides = new Random(System.currentTimeMillis()).nextInt(10) + 3;
        if (radius < 5) radius = new Random(System.currentTimeMillis()).nextInt(50) + 20;

        MovableUnit am = new MovableUnit(new StraightMotion());
        double basePhi = 2 * Math.PI / nSides;
        Point2D firstPoint = new Point2D.Double();
        Point2D point1 = new Point2D.Double();
        Point2D point2 = new Point2D.Double();
        point1.setLocation(radius * Math.cos(0) + location.getX(), radius * Math.sin(0) + location.getY());
        firstPoint.setLocation(point1);
        IBaseNexus tempChain;
        IBaseTransformer tempTransformer;
        for (int i = 1; i < nSides; i++) {
            point2.setLocation(radius * Math.cos(i * basePhi) + location.getX(),
                    radius * Math.sin(i * basePhi) + location.getY());
            tempTransformer = new SimpleTransformer(radius);
            if (i % 3 == 0) tempTransformer.negateDirection();
            tempChain = new SimpleNexus(point1, point2, tempTransformer);
            am.push_back(tempChain);
            point1.setLocation(point2);
        }
        tempTransformer = new SimpleTransformer(radius);
        tempTransformer.negateDirection();
        tempChain = new SimpleNexus(point1, firstPoint, tempTransformer);
        am.push_back(tempChain);

        return am;
    }

}
