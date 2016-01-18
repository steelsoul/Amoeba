package ua.odessa.xand.amoeba.renderer.api;

import java.awt.Component;
import java.awt.event.ActionListener;

public interface IBaseRenderer extends ActionListener{
	Component getGUI();
    void generateUnit(String initiator);
}
