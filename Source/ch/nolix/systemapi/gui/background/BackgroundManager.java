/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.gui.guiproperty.ImageApplication;

/**
 * @author Silvan Wyss
 * @param <B> the type of a {@link BackgroundManager}.
 */
public interface BackgroundManager<B extends BackgroundManager<B>> {
  /**
   * @return the background of the current {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background
   */
  IBackground getBackground();

  /**
   * @return the background color of the current {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background color
   */
  IColor getBackgroundColor();

  /**
   * @return the background color gradient of the current
   *         {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background color gradient
   */
  IColorGradient getBackgroundColorGradient();

  /**
   * @return the background image of the current {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background image
   */
  Image getBackgroundImage();

  /**
   * @return the {@link ImageApplication} of the background image of the current
   *         {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background image
   */
  ImageApplication getBackgroundImageApplication();

  /**
   * @return the {@link BackgroundType} of the current {@link BackgroundManager}
   * @throws RuntimeException if the current {@link BackgroundManager} does not
   *                          have a background
   */
  BackgroundType getBackgroundType();

  /**
   * @return true if the current {@link BackgroundManager} has a background, false
   *         otherwise
   */
  boolean hasBackground();

  /**
   * Removes the background of the current {@link BackgroundManager}.
   */
  void removeBackground();

  /**
   * Sets the given backgroundColor as background to the current
   * {@link BackgroundManager}.
   * 
   * @param backgroundColor
   * @return the current {@link BackgroundManager}
   */
  B setBackgroundColor(IColor backgroundColor);

  /**
   * Sets the given backgroundColorGradient as background to the current
   * {@link BackgroundManager}.
   * 
   * @param backgroundColorGradient
   * @return the current {@link BackgroundManager}
   */
  B setBackgroundColorGradient(IColorGradient backgroundColorGradient);

  /**
   * Sets the given backgroundImage as background to the current
   * {@link BackgroundManager}.
   * 
   * @param backgroundImage
   * @return the current {@link BackgroundManager}
   */
  B setBackgroundImage(Image backgroundImage);

  /**
   * Sets the given backgroundImage with the given imageApplication as background
   * to the current {@link BackgroundManager}.
   * 
   * @param backgroundImage
   * @param imageApplication
   * @return the current {@link BackgroundManager}
   */
  B setBackgroundImage(Image backgroundImage, ImageApplication imageApplication);
}
