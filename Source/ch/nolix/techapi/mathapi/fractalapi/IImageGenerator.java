//package declaration
package ch.nolix.techapi.mathapi.fractalapi;

import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

//interface
public interface IImageGenerator extends IFuture {

  // method declaration
  IMutableImage<?> getStoredImage();
}
