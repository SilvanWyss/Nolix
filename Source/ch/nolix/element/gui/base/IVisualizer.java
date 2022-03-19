//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.core.rasterapi.Rectangular;

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
