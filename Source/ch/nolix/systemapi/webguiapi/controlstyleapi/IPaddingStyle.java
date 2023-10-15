//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IPaddingStyle<PS extends IPaddingStyle<PS>> {

  // method declaration
  int getBottomPaddingWhenHasState(ControlState state);

  // method declaration
  int getLeftPaddingWhenHasState(ControlState state);

  // method declaration
  int getRightPaddingWhenHasState(ControlState state);

  // method declaration
  int getTopPaddingWhenHasState(ControlState state);

  // method declaration
  void removeCustomBottomPaddings();

  // method declaration
  void removeCustomLeftPaddings();

  // method declaration
  void removeCustomPaddings();

  // method declaration
  void removeCustomRightPaddings();

  // method declaration
  void removeCustomTopPaddings();

  // method declaration
  PS setBottomPaddingForState(ControlState state, int bottomPadding);

  // method declaration
  PS setLeftPaddingForState(ControlState state, int leftPadding);

  // method declaration
  PS setPaddingForState(ControlState state, int padding);

  // method declaration
  PS setRightPaddingForState(ControlState state, int rightPadding);

  // method declaration
  PS setTopPaddingForState(ControlState state, int topPadding);
}
