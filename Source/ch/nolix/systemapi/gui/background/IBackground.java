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

/**
 * @author Silvan Wyss
 */
public interface IBackground extends Element {
  IColor getColor();

  IColorGradient getColorGradient();

  Image getImage();

  ImageApplication getImageApplication();

  BackgroundType getType();

  ExtendedIterable<ICssProperty> toCssProperties();
}
