package ua.odessa.xand.amoeba.controller.api;

import java.awt.Component;

public interface IBaseControlElement {
	void enable(boolean state);
	Component getGUI();
	boolean isEnabled();
}
