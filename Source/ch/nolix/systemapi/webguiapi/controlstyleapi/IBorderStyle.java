//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//own imports
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//interface
public interface IBorderStyle<BCS extends IBorderStyle<BCS>> {

  // method declaration
  IColor getBottomBorderColorWhenHasState(ControlState state);

  // method declaration
  int getBottomBorderThicknessWhenHasState(ControlState state);

  // method declaration
  IColor getLeftBorderColorWhenHasState(ControlState state);

  // method declaration
  int getLeftBorderThicknessWhenHasState(ControlState state);

  // method declaration
  IColor getRightBorderColorWhenHasState(ControlState state);

  // method declaration
  int getRightBorderThicknessWhenHasState(ControlState state);

  // method declaration
  IColor getTopBorderColorWhenHasState(ControlState state);

  // method declaration
  int getTopBorderThicknessWhenHasState(ControlState state);

  // method declaration
  void removeCustomBorderColors();

  // method declaration
  void removeCustomBorderThicknesses();

  // method declaration
  void removeCustomBottomBorderColors();

  // method declaration
  void removeCustomBottomBorderThicknesses();

  // method declaration
  void removeCustomLeftBorderColors();

  // method declaration
  void removeCustomLeftBorderThicknesses();

  // method declaration
  void removeCustomRightBorderColors();

  // method declaration
  void removeCustomRightBorderThicknesses();

  // method declaration
  void removeCustomTopBorderColors();

  // method declaration
  void removeCustomTopBorderThicknesses();

  // method declaration
  BCS setBorderColorForState(ControlState state, IColor borderColor);

  // method declaration
  BCS setBorderThicknessForState(ControlState state, int borderThickness);

  // method declaration
  BCS setBottomBorderColorForState(ControlState state, IColor bottomBorderColor);

  // method declaration
  BCS setBottomBorderThicknessForState(ControlState state, int bottomBorderThickness);

  // method declaration
  BCS setLeftBorderColorForState(ControlState state, IColor leftBorderColor);

  // method declaration
  BCS setLeftBorderThicknessForState(ControlState state, int leftBorderThickness);

  // method declaration
  BCS setRightBorderColorForState(ControlState state, IColor rightBorderColor);

  // method declaration
  BCS setRightBorderThicknessForState(ControlState state, int rightBorderThickness);

  // method declaration
  BCS setTopBorderColorForState(ControlState state, IColor topBorderColor);

  // method declaration
  BCS setTopBorderThicknessForState(ControlState state, int topBorderThickness);
}
