/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;

/**
 * @author Silvan Wyss
 */
public interface IBackground extends IElement {
  IColor getColor();

  IColorGradient getColorGradient();

  IImage getImage();

  ImageApplication getImageApplication();

  BackgroundType getType();

  IContainer<ICssProperty> toCssProperties();
}
