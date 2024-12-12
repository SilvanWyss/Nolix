package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.backgroundapi.IBackground;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IBackgroundStyle<S extends IBackgroundStyle<S>> {

  IBackground getBackgroundWhenHasState(ControlState state);

  void removeCustomBackgrounds();

  S setBackgroundColorForState(ControlState state, IColor backgroundColor);

  S setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);

  S setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);

  S setBackgroundForState(ControlState state, IBackground background);
}
