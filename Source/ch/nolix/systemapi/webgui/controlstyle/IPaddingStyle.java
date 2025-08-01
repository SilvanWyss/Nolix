package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface IPaddingStyle<S extends IPaddingStyle<S>> {

  IAbsoluteOrRelativeInt getBottomPaddingWhenHasState(ControlState state);

  IAbsoluteOrRelativeInt getLeftPaddingWhenHasState(ControlState state);

  IAbsoluteOrRelativeInt getRightPaddingWhenHasState(ControlState state);

  IAbsoluteOrRelativeInt getTopPaddingWhenHasState(ControlState state);

  void removeCustomBottomPaddings();

  void removeCustomLeftPaddings();

  void removeCustomPaddings();

  void removeCustomRightPaddings();

  void removeCustomTopPaddings();

  S setBottomPaddingForState(ControlState state, int bottomPadding);

  S setLeftPaddingForState(ControlState state, int leftPadding);

  S setPaddingForState(ControlState state, int padding);

  S setRightPaddingForState(ControlState state, int rightPadding);

  S setTopPaddingForState(ControlState state, int topPadding);
}
