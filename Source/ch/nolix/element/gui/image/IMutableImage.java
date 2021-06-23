//package declaration
package ch.nolix.element.gui.image;

//own imports
import ch.nolix.element.gui.color.Color;

//interface
public interface IMutableImage<MI extends IMutableImage<MI>> extends IImage<MI> {
	
	//method declaration
	MI setPixel(int xPosition, int yPosition, Color color);
}
