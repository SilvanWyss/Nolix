//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.rasterAPI.Rectangular;

//interface
public interface IVisualizer extends Rectangular {
	
	//abstract method
	public abstract int getViewAreaCursorXPosition();
	
	//abstract method
	public abstract int getViewAreaCursorYPosition();
	
	//abstract method
	public abstract int getViewAreaHeight();
	
	//abstract method
	public abstract int getViewAreaWidth();
	
	//abstract method
	public abstract void initialize(GUI<?> parentGUI);
	
	//abstract method
	public abstract void noteClose();
	
	//abstract method
	public abstract void repaint();
}
