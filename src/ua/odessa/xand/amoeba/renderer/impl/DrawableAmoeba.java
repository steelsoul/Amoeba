package ua.odessa.xand.amoeba.renderer.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import ua.odessa.xand.amoeba.model.system.amoeba.units.impl.AmoebaUnit;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;
import ua.odessa.xand.amoeba.renderer.api.IMovable;
import ua.odessa.xand.mathStuff.MathHelper;

public class DrawableAmoeba implements IDrawable, IMovable {
		
	private AmoebaUnit mModel;
	private Color mColor; 
	private boolean mSelected;
    private int mDetailsLevel;

    public DrawableAmoeba(AmoebaUnit model, int rc, int gc, int bc){
        mModel = model;
        mColor = new Color(rc, gc, bc);
        mSelected = false;
        mDetailsLevel = 0;
    }
	
	@Override
	public void setDetailsLevel(int level) {
		mDetailsLevel = level;
	}
	
	@Override
	public int getDetailsLevel() {
		return mDetailsLevel;
	}

    @Override
    public void setCollisionCheck(boolean state) {
//        ((MovableUnit)mModel).enableCDS(state);
    }

    @Override
    public boolean isCollisionChecked() {
//        return ((MovableUnit)mModel).isCDSactive();
    	return false;
    }

    @Override
	public void draw(Graphics g) {
        mModel.transform();
		g.setColor(mColor);		
		
		if (mDetailsLevel >= 0) {
			// just show amoeba shape
			Vector<Line2D> shape = mModel.getShape();
            Stroke mineStroke = ((Graphics2D)g).getStroke();
			if (mSelected)
                ((Graphics2D)g).setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
			for (Line2D temp: shape)
				g.drawLine((int)temp.getX1(), (int)temp.getY1(), (int)temp.getX2(), (int)temp.getY2());
            ((Graphics2D)g).setStroke(mineStroke);
		}
		
		if (mDetailsLevel >= 1) {
			// show inner base figure
			g.setColor(Color.GRAY);
			g.drawPolygon(mModel.getBase());
            g.setColor(mColor);
            Point2D center = MathHelper.getCenter(mModel.getBounds());
//            if ( ((MovableUnit)mModel).isCDSactive() )
//                g.drawString("C", (int)center.getX(), (int)center.getY());

			Point runTo = mModel.getDestination();
			if (runTo != null) {
				g.setColor(mColor.brighter());
				Stroke mineStroke = ((Graphics2D)g).getStroke();
				float dash[] = { 3.0f };
				((Graphics2D)g).setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
				g.drawLine(runTo.x, runTo.y, (int)center.getX(), (int)center.getY());
				((Graphics2D)g).setStroke(mineStroke);
			}
		}
		
		if (mDetailsLevel >= 2) {
			// show control points
			g.setColor(Color.PINK);
			Vector<Line2D> controls = mModel.getControls();
			for (Line2D temp: controls) {
				g.drawOval((int)temp.getX1() - 2, (int)temp.getY1() - 2, 4, 4);
				g.drawOval((int)temp.getX2() - 2, (int)temp.getY2() - 2, 4, 4);
			}
			Point2D center = MathHelper.getCenter(mModel.getBounds());
			g.drawOval((int)center.getX() - 2, (int)center.getY() - 2, 4, 4);				
		}
	}	
	
	@Override
	public boolean isPointFit(Point p) {
		return mModel.getBase().contains(p);
	}
	
	@Override
	public void select() {
		mSelected = !mSelected;
	}
	
	@Override
	public void select(boolean value) {
		mSelected = value;
	}
	
	@Override
	public boolean isSelected() {
		return mSelected;
	}

	@Override
	public Color getColor() {
		return mColor;
	}

	@Override
	public void setColor(Color color) {
		mColor = color;		
	}
	
	@Override
	public void move(Point to) {
        mModel.move(to);
	}

    @Override
    public void stop(boolean forced) {
        mModel.stop(forced);
    }

}
