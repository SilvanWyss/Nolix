/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapi.math.fractal;

import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.systemapi.graphic.image.IMutableImage;

/**
 * @author Silvan Wyss
 */
public interface IImageGenerator extends IFuture {
  IMutableImage<?> getStoredImage();
}
