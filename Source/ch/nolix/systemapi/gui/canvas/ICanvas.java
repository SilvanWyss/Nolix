package ch.nolix.systemapi.gui.canvas;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.graphic.image.ImageApplication;
import ch.nolix.systemapi.gui.background.BackgroundType;
import ch.nolix.systemapi.gui.background.IBackground;

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
