package ua.odessa.xand.amoeba.model.system.amoeba.units.impl;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseMotion;
import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.units.api.IMovableUnit;
import ua.odessa.xand.amoeba.model.system.amoeba.units.api.ITransformableUnit;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 23.06.13
 * Time: 1:20
 */

public class MovableUnit extends SimpleUnit implements IMovableUnit, ITransformableUnit {
    private IBaseMotion mMotionSystem;

    public MovableUnit(IBaseMotion motionSystem) {
        mMotionSystem = motionSystem;
        mMotionSystem.assignUnit(this);
    }

    @Override
    public void move(Point to) {
        mMotionSystem.start(to);
    }

    @Override
    public void stop(boolean forced) {
        mMotionSystem.stop(forced);
    }

    @Override
    public void enableCDS(boolean state) {
        mMotionSystem.enableCDS(state);
    }

    @Override
    public boolean isCDSactive() {
        return mMotionSystem.isCDSOn();
    }

    @Override
    public Point getDestination() {
        return mMotionSystem.getDestination();
    }

    @Override
    public void transform() {
        // recalculate control points according to the transformation
        for (int i = 0; i < mNexusList.size(); i++) {
            int j = (i < mNexusList.size() - 1) ? i + 1 : 0;
            Point2D first = mNexusList.get(i).generateControl();
            mNexusList.get(j).setFirstControl(first);
        }

        for (IBaseNexus bn: mNexusList)
            bn.transform();
    }
}
