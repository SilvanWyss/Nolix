package ch.nolix.systemapi.gui.background;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;

public interface IBackgroundHolder<C extends IBackgroundHolder<C>> {

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
