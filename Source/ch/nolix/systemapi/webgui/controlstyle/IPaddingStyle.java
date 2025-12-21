package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IPaddingStyle}.
 */
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

  S forStateSetBottomPadding(ControlState state, int bottomPadding);

  S forStateSetLeftPadding(ControlState state, int leftPadding);

  S forStateSetPadding(ControlState state, int padding);

  S forStateSetRightPadding(ControlState state, int rightPadding);

  S forStateSetTopPadding(ControlState state, int topPadding);
}
