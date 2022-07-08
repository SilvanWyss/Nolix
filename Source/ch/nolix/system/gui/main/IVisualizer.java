//package declaration
package ch.nolix.system.gui.main;

//own imports
import ch.nolix.coreapi.geometryapi.griduniversalapi.Rectangular;

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
