package ua.odessa.xand.amoeba.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;

public class SimpleModel implements BaseModel {
	private List<IDrawable> mUnits;
	
	public SimpleModel() {
		mUnits = new ArrayList<IDrawable>();
	}
	
	@Override
	public void add(IDrawable unit) {
		if (!mUnits.contains(unit))
			mUnits.add(unit);
	}

	@Override
	public void remove(IDrawable unit) {
		if (mUnits.contains(unit)) 
			mUnits.remove(unit);		
	}

    @Override
    public void removeSelected() {
        Vector<IDrawable> ri = new Vector<IDrawable>();
        for (IDrawable u: mUnits)
            if (u.isSelected()) ri.add(u);
        mUnits.removeAll(ri);
    }

	@Override
	public IDrawable getUnit(int index) {
		if (mUnits.size() > index && index >= 0) 
			return mUnits.get(index);
		else
		return null;
	}

    @Override
    public IDrawable getSelected() {
        for (IDrawable u: mUnits)
            if (u.isSelected())
                return u;
        return null;
    }

    @Override
    public Vector<IDrawable> getUnits() {
        Vector<IDrawable> vu = new Vector<IDrawable>();
        vu.addAll(mUnits);
        return vu;
    }

    @Override
	public int getSize() {
		return mUnits.size();
	}

}
