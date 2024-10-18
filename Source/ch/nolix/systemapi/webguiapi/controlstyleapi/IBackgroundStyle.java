package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IBackgroundStyle<BCS extends IBackgroundStyle<BCS>> {

  IBackground getBackgroundWhenHasState(ControlState state);

  void removeCustomBackgrounds();

  BCS setBackgroundColorForState(ControlState state, IColor backgroundColor);

  BCS setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);

  BCS setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);

  BCS setBackgroundForState(ControlState state, IBackground background);
}
