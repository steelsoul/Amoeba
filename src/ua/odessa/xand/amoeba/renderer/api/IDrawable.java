package ua.odessa.xand.amoeba.renderer.api;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public interface IDrawable {
	public static final int MAX_DETAILS_LEVEL = 2;
	public Color getColor();
	void setColor(Color color);
	void draw(Graphics g);
	void setDetailsLevel(int level);
	int getDetailsLevel();
	boolean isPointFit(Point p);
	boolean isSelected();
	void select(boolean value);
	void select();
}
