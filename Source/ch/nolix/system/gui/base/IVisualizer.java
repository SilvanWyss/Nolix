//package declaration
package ch.nolix.system.gui.base;

import ch.nolix.core.griduniversalapi.Rectangular;

//interface
public interface IVisualizer extends Rectangular {
	
	//method declaration
	int getViewAreaCursorXPosition();
	
	//method declaration
	int getViewAreaCursorYPosition();
	
	//method declaration
	int getViewAreaHeight();
	
	//method declaration
	int getViewAreaWidth();
	
	//method declaration
	void initialize(GUI<?> parentGUI);
	
	//method declaration
	void noteClose();
	
	//method declaration
	void repaint();
}
