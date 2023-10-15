//package declaration
package ch.nolix.systemapi.guiapi.canvasapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;

//interface
public interface ICanvas<C extends ICanvas<C>> {

  // method declaration
  IBackground getBackground();

  // method declaration
  IColor getBackgroundColor();

  // method declaration
  IColorGradient getBackgroundColorGradient();

  // method declaration
  IImage getBackgroundImage();

  // method declaration
  ImageApplication getBackgroundImageApplication();

  // method declaration
  BackgroundType getBackgroundType();

  // method declaration
  boolean hasBackground();

  // method declaration
  void removeBackground();

  // method declaration
  C setBackgroundColor(IColor backgroundColor);

  // method declaration
  C setBackgroundColorGradient(IColorGradient backgroundColorGradient);

  // method declaration
  C setBackgroundImage(IImage backgroundImage);

  // method declaration
  C setBackgroundImage(IImage backgroundImage, ImageApplication imageApplication);
}
