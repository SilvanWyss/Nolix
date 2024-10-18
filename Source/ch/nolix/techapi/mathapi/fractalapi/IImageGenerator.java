package ch.nolix.techapi.mathapi.fractalapi;

import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

public interface IImageGenerator extends IFuture {

  IMutableImage<?> getStoredImage();
}
