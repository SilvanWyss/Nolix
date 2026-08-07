/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;

/**
 * @author Silvan Wyss
 * @param <B> the type of a {@link BackgroundHolder}.
 */
public interface BackgroundHolder<B extends BackgroundHolder<B>> {
  IBackground getBackground();

  IColor getBackgroundColor();

  IColorGradient getBackgroundColorGradient();

  IImage getBackgroundImage();

  ImageApplication getBackgroundImageApplication();

  BackgroundType getBackgroundType();

  boolean hasBackground();

  void removeBackground();

  B setBackgroundColor(IColor backgroundColor);

  B setBackgroundColorGradient(IColorGradient backgroundColorGradient);

  B setBackgroundImage(IImage backgroundImage);

  B setBackgroundImage(IImage backgroundImage, ImageApplication imageApplication);
}
