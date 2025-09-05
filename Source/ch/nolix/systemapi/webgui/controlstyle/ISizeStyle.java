package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface ISizeStyle<S extends ISizeStyle<S>> {
  boolean definesHeightForState(ControlState state);

  boolean definesWidthForState(ControlState state);

  IAbsoluteOrRelativeInt getHeightForState(ControlState state);

  IAbsoluteOrRelativeInt getWidthForState(ControlState state);

  void removeCustomHeights();

  void removeCustomWidths();

  S setHeightForState(ControlState state, int height);

  S setHeightInPercentOfViewAreaForState(ControlState state, double heightInPercentOfViewAreaHeight);

  S setWidthForState(ControlState state, int width);

  S setWidthInPercentOfViewAreaWidthForState(ControlState state, double widthInPercentOfViewAreaWidth);
}
