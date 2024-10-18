package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IPaddingStyle<PS extends IPaddingStyle<PS>> {

  int getBottomPaddingWhenHasState(ControlState state);

  int getLeftPaddingWhenHasState(ControlState state);

  int getRightPaddingWhenHasState(ControlState state);

  int getTopPaddingWhenHasState(ControlState state);

  void removeCustomBottomPaddings();

  void removeCustomLeftPaddings();

  void removeCustomPaddings();

  void removeCustomRightPaddings();

  void removeCustomTopPaddings();

  PS setBottomPaddingForState(ControlState state, int bottomPadding);

  PS setLeftPaddingForState(ControlState state, int leftPadding);

  PS setPaddingForState(ControlState state, int padding);

  PS setRightPaddingForState(ControlState state, int rightPadding);

  PS setTopPaddingForState(ControlState state, int topPadding);
}
