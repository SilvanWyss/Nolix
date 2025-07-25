package ch.nolix.techapi.mathapi.fractalapi;

import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.systemapi.graphic.image.IMutableImage;

public interface IImageGenerator extends IFuture {

  IMutableImage<?> getStoredImage();
}
