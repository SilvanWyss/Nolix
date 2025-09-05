package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface IBackgroundStyle<S extends IBackgroundStyle<S>> {
  IBackground getBackgroundWhenHasState(ControlState state);

  void removeCustomBackgrounds();

  S setBackgroundColorForState(ControlState state, IColor backgroundColor);

  S setBackgroundColorGradientForState(ControlState state, IColorGradient backgroundColorGradient);

  S setBackgroundImageForState(ControlState state, IImage backgroundImage, ImageApplication imageApplication);

  S setBackgroundForState(ControlState state, IBackground background);
}
