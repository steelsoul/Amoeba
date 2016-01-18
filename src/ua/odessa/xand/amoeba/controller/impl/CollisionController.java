package ua.odessa.xand.amoeba.controller.impl;

import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;
import ua.odessa.xand.amoeba.renderer.api.IMovable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 20.06.13
 * Time: 22:43
 */
public class CollisionController implements IBaseControlElement {
    public static final String MyName = "CollisionCTRL";
    private final JCheckBox mBox;
    private final BaseModel mModel;

    public CollisionController(BaseModel model) {
        mModel = model;
        mBox = new JCheckBox("Check collision", false);
        mBox.setEnabled(false);
        mBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean state = mBox.isSelected();
                for (IDrawable u : mModel.getUnits())
                    if (u.isSelected()) {
                        ((IMovable)u).setCollisionCheck(state);
                    }
            }
        });
    }

    @Override
    public void enable(boolean state) {
        IMovable sel = (IMovable)mModel.getSelected();
        if (sel != null)
            mBox.setSelected(sel.isCollisionChecked());
        mBox.setEnabled(state);
    }

    @Override
    public Component getGUI() {
        return mBox;
    }

    @Override
    public boolean isEnabled() {
        return mBox.isEnabled();
    }
}
