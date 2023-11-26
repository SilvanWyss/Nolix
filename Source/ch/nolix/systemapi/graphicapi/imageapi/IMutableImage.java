//package declaration
package ch.nolix.systemapi.graphicapi.imageapi;

//own imports
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

//interface
public interface IMutableImage<MI extends IMutableImage<MI>> extends IImage {

  //method declaration
  MI setPixel(int xPosition, int yPosition, IColor color);
}
