package ua.odessa.xand.amoeba.controller.impl;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ua.odessa.xand.amoeba.model.api.BaseModel;
import ua.odessa.xand.amoeba.controller.api.IBaseControlElement;
import ua.odessa.xand.amoeba.controller.api.IBaseController;
import ua.odessa.xand.amoeba.renderer.api.IDrawable;
import ua.odessa.xand.amoeba.renderer.api.IMovable;

public class SimpleController extends JPanel implements IBaseController {

	private static final long serialVersionUID = 1L;
	
	private BaseModel mModel;
	
	// external controlling elements
	private HashMap<String, IBaseControlElement> mControls;

	public SimpleController(BaseModel model) {
        LayoutManager composition = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(composition);
        mModel = model;
		mControls = new HashMap<String, IBaseControlElement>();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
		// TODO Auto-generated method stub
//		mRenderer.processMousePressed(e, this);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        // selection -- left key
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            boolean areAnySelected = false;
            for (IDrawable u: mModel.getUnits()) {
                if (u.isPointFit(e.getPoint())) {
                    u.select();
                    areAnySelected = true;
                }
            }
            // selection - reset -- left key on empty field
            if (!areAnySelected) {
                for (IDrawable u: mModel.getUnits())
                    u.select(false);
            }
        }
        else
        // moving -- right key
        if (e.getButton() == MouseEvent.BUTTON3)
            for (IDrawable u: mModel.getUnits()) {
                if (u.isSelected()) {
                    u.select(false);
                    ((IMovable)u).move(e.getPoint());
                }
            }

        // set erase key and slider activity
        mControls.get(EraserController.MyName).enable(false);
        mControls.get(DetailsLevelController.MyName).enable(false);
        mControls.get(CollisionController.MyName).enable(false);
        for (IDrawable u: mModel.getUnits())
            if (u.isSelected()) {
                mControls.get(EraserController.MyName).enable(true);
                mControls.get(DetailsLevelController.MyName).enable(true);
                mControls.get(CollisionController.MyName).enable(true);
                break;
            }
	}

	@Override
	public void addElement(IBaseControlElement e, String uniqueName) {
		if (!mControls.containsKey(uniqueName))
		{
			mControls.put(uniqueName, e);
			this.add(Box.createVerticalStrut(10));
			this.add(e.getGUI());		
		}
	}
	@Override
	public void removeElement(String uniqueName) {
		if (mControls.containsKey(uniqueName)) {
			IBaseControlElement found = mControls.get(uniqueName);
            this.remove(found.getGUI());
            mControls.remove(found);
		}
	}

	@Override
	public void enableElement(String uniqueName, boolean value) {
		if (mControls.containsKey(uniqueName)) {
			IBaseControlElement found = mControls.get(uniqueName);
			found.enable(value);	
		}
	}

	@Override
	public boolean isEnabled(String uniqueName) {
		if (mControls.containsKey(uniqueName)) {
			IBaseControlElement found = mControls.get(uniqueName);
			return found.isEnabled();
		}
		else
		return false;
	}

	@Override
	public Component getGUI() {
		return this;
	}

}
