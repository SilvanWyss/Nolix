/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.graphic.image;

import ch.nolix.baseapi.foundation.copy.Copyable;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 * @param <I> us the type of a {@link IMutableImage}.
 */
public interface IMutableImage<I extends IMutableImage<I>> extends Copyable<I>, Image {
  /**
   * Sets the given color to the pixel at the given xPosition and yPosition of the
   * current {@link IMutableImage}.
   * 
   * @param xPosition
   * @param yPosition
   * @param color
   * @return the current {@link IMutableImage}
   */
  I setPixel(int xPosition, int yPosition, IColor color);
}
