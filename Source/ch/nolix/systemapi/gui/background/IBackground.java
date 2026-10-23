/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.gui.guiproperty.ImageApplication;

/**
 * @author Silvan Wyss
 */
public interface IBackground extends Element {
  /**
   * @return the color of the current {@link IBackground}
   * @throws RuntimeException if the current {@link IBackground} is not a color
   */
  IColor getColor();

  /**
   * @return the color gradient of the current {@link IBackground}
   * @throws RuntimeException if the current {@link IBackground} is not a color
   *                          gradient
   */
  IColorGradient getColorGradient();

  /**
   * @return the image of the current {@link IBackground}
   * @throws RuntimeException if the current {@link IBackground} is not an image
   */
  Image getImage();

  /**
   * @return the {@link ImageApplication} of the image of the current
   *         {@link IBackground}
   * @throws RuntimeException if the current {@link IBackground} is not an image
   */
  ImageApplication getImageApplication();

  /**
   * @return the {@link BackgroundType} of the current {@link IBackground}
   */
  BackgroundType getType();

  /**
   * @return the CSS properties that represent the current {@link IBackground}
   */
  ExtendedIterable<ICssProperty> toCssProperties();
}
