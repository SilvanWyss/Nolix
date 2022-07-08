//package declaration
package ch.nolix.systemapi.guiapi.imageapi;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;

//interface
public interface IMutableImage<MI extends IMutableImage<MI>> extends IImage {
	
	//method declaration
	MI setPixel(int xPosition, int yPosition, IColor color);
}
