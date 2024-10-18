package ch.nolix.systemapi.guiapi.canvasapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.backgroundapi.BackgroundType;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;

public interface ICanvas<C extends ICanvas<C>> {

  IBackground getBackground();

  IColor getBackgroundColor();

  IColorGradient getBackgroundColorGradient();

  IImage getBackgroundImage();

  ImageApplication getBackgroundImageApplication();

  BackgroundType getBackgroundType();

  boolean hasBackground();

  void removeBackground();

  C setBackgroundColor(IColor backgroundColor);

  C setBackgroundColorGradient(IColorGradient backgroundColorGradient);

  C setBackgroundImage(IImage backgroundImage);

  C setBackgroundImage(IImage backgroundImage, ImageApplication imageApplication);
}
