//package declaration
package ch.nolix.element.gui.image;

//own imports
import ch.nolix.element.gui.color.Color;

//interface
public interface IMutableImage extends IImage {
	
	//method declaration
	IMutableImage setPixel(int xPosition, int yPosition, Color color);
}
