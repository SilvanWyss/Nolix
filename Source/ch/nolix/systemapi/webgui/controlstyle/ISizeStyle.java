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

  S forStateSetHeight(ControlState state, int height);

  S forStateSetHeightInPercentOfViewArea(ControlState state, double heightInPercentOfViewAreaHeight);

  S forStateSetWidth(ControlState state, int width);

  S forStateSetWidthInPercentOfViewAreaWidth(ControlState state, double widthInPercentOfViewAreaWidth);
}
