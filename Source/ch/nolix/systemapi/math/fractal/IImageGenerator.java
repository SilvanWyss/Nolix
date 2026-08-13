/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.fractal;

import ch.nolix.baseapi.programcontrol.future.IFuture;
import ch.nolix.systemapi.graphic.image.IMutableImage;

/**
 * @author Silvan Wyss
 */
public interface IImageGenerator extends IFuture {
  IMutableImage<?> getStoredImage();
}
