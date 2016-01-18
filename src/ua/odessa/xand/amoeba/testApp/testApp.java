package ua.odessa.xand.amoeba.testApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.model.impl.SimpleModel;
import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.controller.api.IBaseController;
import ua.odessa.xand.amoeba.controller.impl.*;
import ua.odessa.xand.amoeba.renderer.api.IBaseRenderer;
import ua.odessa.xand.amoeba.renderer.impl.SimpleRenderer;

/**
 * Amoeba client
 * */
public class testApp extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873362189724866977L;
	private static IBaseRenderer mRenderSystem;
	private static IBaseController mController;
	private static BaseModel mModel;
	
	private testApp() {
		mModel = new SimpleModel();
        mController = new SimpleController(mModel);
		mRenderSystem = new SimpleRenderer(mController, mModel);
    }

	void createGUI() {
		JFrame view = new JFrame("Amoeba pool");
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new BorderLayout());
		IBaseControlElement tce = new GeneratorController(mRenderSystem);
		mController.addElement(tce, GeneratorController.MyName);
        tce = new EraserController(mModel);
        mController.addElement(tce, EraserController.MyName);
        tce = new DetailsLevelController(mModel);
        mController.addElement(tce, DetailsLevelController.MyName);
        tce = new CollisionController(mModel);
        mController.addElement(tce, CollisionController.MyName);

		framePanel.add(mController.getGUI(), BorderLayout.WEST);
		framePanel.add(mRenderSystem.getGUI(), BorderLayout.CENTER);
		view.getContentPane().add(framePanel);
		view.setPreferredSize(new Dimension(800, 600));
		view.pack();
		view.setResizable(false);
		view.setVisible(true);
	}
	
	void start() {
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		testApp mApp = new testApp();
		mApp.start();
	}

	@Override
	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				setDefaultLookAndFeelDecorated(true);
				createGUI();
			}
		});
	}

}
