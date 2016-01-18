package ua.odessa.xand.amoeba.controller.impl;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;

public class DetailsLevelController implements IBaseControlElement {
    public static final String MyName = "DetailsCTRL";
	private static final int DefaultTickSpacing = 1;	
	private JSlider mDetailsLevel;
	private BaseModel mModel;
	private boolean mIsDetailsEnabled;
	
	public DetailsLevelController(BaseModel model) {
		mModel = model;
		mIsDetailsEnabled = false;
		mDetailsLevel  = new JSlider(0, IDrawable.MAX_DETAILS_LEVEL, 0);
		mDetailsLevel.setEnabled(false);
		mDetailsLevel.setMajorTickSpacing(DefaultTickSpacing);
		mDetailsLevel.setMinorTickSpacing(DefaultTickSpacing);
		mDetailsLevel.setSnapToTicks(true);
		mDetailsLevel.setPaintTicks(true);
		mDetailsLevel.setPaintLabels(true);
		mDetailsLevel.setFont(new Font("Courier", Font.ITALIC,10));
		
		mDetailsLevel.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent ev) {
				JSlider source = (JSlider)ev.getSource();
				if (!source.getValueIsAdjusting()) {
					if (mIsDetailsEnabled) {
                        for (IDrawable u: mModel.getUnits()) {
                            if (u.isSelected()) {
                                u.setDetailsLevel(source.getValue());
                                u.select(false);
                            }
                        }
                        mIsDetailsEnabled = false;
                        mDetailsLevel.setEnabled(false);
                    }
				}
			}
		});
	}
	
	@Override
	public Component getGUI() {
		return mDetailsLevel;
	}
	
	@Override
	public boolean isEnabled() {
		return mIsDetailsEnabled;
	}
	
	@Override
	public void enable(boolean state) {
        mIsDetailsEnabled = false;
        IDrawable sel = mModel.getSelected();
        if (sel != null)
            mDetailsLevel.setValue(sel.getDetailsLevel());
        mDetailsLevel.setEnabled(state);
        mIsDetailsEnabled = state;
	}
}
