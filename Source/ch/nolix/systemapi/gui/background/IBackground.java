package ch.nolix.systemapi.gui.background;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;

public interface IBackground extends IElement {

  IColor getColor();

  IColorGradient getColorGradient();

  IImage getImage();

  ImageApplication getImageApplication();

  BackgroundType getType();

  IContainer<ICssProperty> toCssProperties();
}
