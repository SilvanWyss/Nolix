//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.rasterAPI.Rectangular;

//interface
public interface IVisualizer extends Rectangular {
	
	//method declaration
	public abstract int getViewAreaCursorXPosition();
	
	//method declaration
	public abstract int getViewAreaCursorYPosition();
	
	//method declaration
	public abstract int getViewAreaHeight();
	
	//method declaration
	public abstract int getViewAreaWidth();
	
	//method declaration
	public abstract void initialize(GUI<?> parentGUI);
	
	//method declaration
	public abstract void noteClose();
	
	//method declaration
	public abstract void repaint();
}
