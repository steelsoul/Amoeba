package ua.odessa.xand.amoeba.model.api;

import ua.odessa.xand.amoeba.renderer.api.IDrawable;

import java.util.Vector;

public interface BaseModel {
	void add(IDrawable unit);
	void remove(IDrawable unit);
    void removeSelected();
    IDrawable getUnit(int index);
    IDrawable getSelected();
    Vector<IDrawable> getUnits();
	int getSize();
}
