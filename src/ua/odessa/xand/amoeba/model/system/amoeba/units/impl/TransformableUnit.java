package ua.odessa.xand.amoeba.model.system.amoeba.units.impl;

import ua.odessa.xand.amoeba.model.system.amoeba.api.IBaseNexus;
import ua.odessa.xand.amoeba.model.system.amoeba.units.api.ITransformableUnit;

import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 24.06.13
 * Time: 13:46
 */
public class TransformableUnit extends SimpleUnit implements ITransformableUnit {

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
