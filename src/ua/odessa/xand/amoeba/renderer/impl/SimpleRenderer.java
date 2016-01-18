package ua.odessa.xand.amoeba.renderer.impl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;

import ua.odessa.xand.amoeba.model.system.amoeba.units.impl.AmoebaUnit;
import ua.odessa.xand.amoeba.model.system.amoeba.units.impl.MovableUnit;
import ua.odessa.xand.amoeba.factory.AmoebaFactory;
import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.controller.api.IBaseController;
import ua.odessa.xand.amoeba.renderer.api.IBaseRenderer;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;

public class SimpleRenderer extends Canvas implements IBaseRenderer {

	private static final long serialVersionUID = 1L;
	private static final int renderFrequency = 25;
	private static final int milliseconds = 1000;
	private static final int renderTimeout = milliseconds / renderFrequency;
	private IBaseController mController;
    private BaseModel mModel;
	private BufferedImage mBuffer;

	public SimpleRenderer(IBaseController controller, BaseModel model) {
		mController = controller;
        mModel = model;
		setPreferredSize(new Dimension(600, 600));
		mBuffer = null;
        Timer renderTicks = new Timer(renderTimeout, this);
		renderTicks.start();
        addMouseListener(controller);
	}

    @Override
    public void generateUnit(String Initiator) {
        Random gen = new Random(System.currentTimeMillis());
        int radius = gen.nextInt(60) + 40;
        int nSides = gen.nextInt(7) + 3;
        Point2D location = new Point2D.Double
                ((getWidth()  - 2*radius)*gen.nextDouble() + radius
                ,(getHeight() - 2*radius)*gen.nextDouble() + radius);
        MovableUnit movableAmoeba = AmoebaFactory.createMovableAmoeba(radius, nSides, location);
        DrawableAmoeba amoeba = new DrawableAmoeba(new AmoebaUnit(movableAmoeba), gen.nextInt(200) + 55, gen.nextInt(200) + 55, gen.nextInt(200) + 55);
        //IDrawable unit = new DrawableAmoeba(model, gen.nextInt(200) + 55, gen.nextInt(200) + 55, gen.nextInt(200) + 55);
        mModel.add(amoeba);
        mController.enableElement(Initiator, true);
    }
	
	void draw(Graphics g) {
		for (IDrawable unit: mModel.getUnits())
			unit.draw(g);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		draw(g);
	}
	
	public void update(Graphics g)
	{
		if (mBuffer == null)
		{
			mBuffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
		}		
		paint(mBuffer.getGraphics());
		g.drawImage(mBuffer, 0, 0, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public Component getGUI() {
		return this;
	}

}
