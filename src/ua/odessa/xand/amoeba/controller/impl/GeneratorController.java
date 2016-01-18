package ua.odessa.xand.amoeba.controller.impl;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.renderer.api.IBaseRenderer;

public class GeneratorController implements IBaseControlElement {
	public static final String MyName = "GeneratorCTRL";

	private IBaseRenderer mRenderer;
	private JButton mButtonGenerate;
	
	public GeneratorController(IBaseRenderer renderer) {
		mRenderer = renderer;
		mButtonGenerate = new JButton("Generate");
		mButtonGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                mButtonGenerate.setEnabled(false);
                mRenderer.generateUnit(MyName);
            }
		});
	}	
	
	@Override
	public void enable(boolean state) {
		mButtonGenerate.setEnabled(state);
	}

	@Override
	public Component getGUI() {
		return mButtonGenerate;
	}

	@Override
	public boolean isEnabled() {
		return mButtonGenerate.isEnabled();
	}

}
