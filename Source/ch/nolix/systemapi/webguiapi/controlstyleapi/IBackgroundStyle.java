//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.canvasapi.IBackground;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IBackgroundStyle<BCS extends IBackgroundStyle<BCS>> {

  // method declaration
  IBackground getBackgroundWhenHasState(ControlState state);

  // method declaration
  void removeCustomBackgrounds();

  // method declaration
  BCS setBackgroundColorForState(ControlState state, IColor backgroundColor);

  // method declaration
  BCS setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);

  // method declaration
  BCS setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);

  // method declaration
  BCS setBackgroundForState(ControlState state, IBackground background);
}
