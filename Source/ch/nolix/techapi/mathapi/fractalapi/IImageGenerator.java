package ch.nolix.techapi.mathapi.fractalapi;

import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

public interface IImageGenerator extends IFuture {

  IMutableImage<?> getStoredImage();
}
