package ua.odessa.xand.amoeba.controller.impl;

import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.model.api.BaseModel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Александр
 * Date: 20.06.13
 * Time: 16:02
 *  */
public class EraserController implements IBaseControlElement {
    public static final String MyName = "EraseUnitCTRL";
    private BaseModel mModel;
    private JButton mButton;

    public EraserController(BaseModel model) {
        mModel = model;
        mButton = new JButton("Delete unit");
        mButton.setEnabled(false);
        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mModel.removeSelected();
                mButton.setEnabled(false);
            }
        });
    }

    @Override
    public void enable(boolean state) {
        mButton.setEnabled(state);
    }

    @Override
    public Component getGUI() {
        return mButton;
    }

    @Override
    public boolean isEnabled() {
        return mButton.isEnabled();
    }
}
