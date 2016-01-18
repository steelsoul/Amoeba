package ua.odessa.xand.amoeba.controller.api;

import java.awt.Component;
import java.awt.event.MouseListener;


public interface IBaseController extends MouseListener{
	void addElement(IBaseControlElement e, String uniqueName);
	void removeElement(String name);
	void enableElement(String name, boolean value);
	boolean isEnabled(String name);
	Component getGUI();
}
