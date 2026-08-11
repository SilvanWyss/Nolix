/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.Image;

/**
 * @author Silvan Wyss
 * @param <B> the type of a {@link BackgroundHolder}.
 */
public interface BackgroundHolder<B extends BackgroundHolder<B>> {
  IBackground getBackground();

  IColor getBackgroundColor();

  IColorGradient getBackgroundColorGradient();

  Image getBackgroundImage();

  ImageApplication getBackgroundImageApplication();

  BackgroundType getBackgroundType();

  boolean hasBackground();

  void removeBackground();

  B setBackgroundColor(IColor backgroundColor);

  B setBackgroundColorGradient(IColorGradient backgroundColorGradient);

  B setBackgroundImage(Image backgroundImage);

  B setBackgroundImage(Image backgroundImage, ImageApplication imageApplication);
}
