package ch.nolix.systemapi.guiapi.backgroundapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;

public interface IBackground extends IElement {

  IColor getColor();

  IColorGradient getColorGradient();

  IImage getImage();

  ImageApplication getImageApplication();

  BackgroundType getType();

  IContainer<ICssProperty> toCssProperties();
}
