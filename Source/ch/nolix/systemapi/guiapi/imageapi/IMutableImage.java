//package declaration
package ch.nolix.systemapi.guiapi.imageapi;

import ch.nolix.system.gui.color.Color;

//interface
public interface IMutableImage<MI extends IMutableImage<MI>> extends IImage<MI> {
	
	//method declaration
	MI setPixel(int xPosition, int yPosition, Color color);
}
