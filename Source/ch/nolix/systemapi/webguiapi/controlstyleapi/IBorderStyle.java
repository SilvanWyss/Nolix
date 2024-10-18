package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IBorderStyle<BCS extends IBorderStyle<BCS>> {

  IColor getBottomBorderColorWhenHasState(ControlState state);

  int getBottomBorderThicknessWhenHasState(ControlState state);

  IColor getLeftBorderColorWhenHasState(ControlState state);

  int getLeftBorderThicknessWhenHasState(ControlState state);

  IColor getRightBorderColorWhenHasState(ControlState state);

  int getRightBorderThicknessWhenHasState(ControlState state);

  IColor getTopBorderColorWhenHasState(ControlState state);

  int getTopBorderThicknessWhenHasState(ControlState state);

  void removeCustomBorderColors();

  void removeCustomBorderThicknesses();

  void removeCustomBottomBorderColors();

  void removeCustomBottomBorderThicknesses();

  void removeCustomLeftBorderColors();

  void removeCustomLeftBorderThicknesses();

  void removeCustomRightBorderColors();

  void removeCustomRightBorderThicknesses();

  void removeCustomTopBorderColors();

  void removeCustomTopBorderThicknesses();

  BCS setBorderColorForState(ControlState state, IColor borderColor);

  BCS setBorderThicknessForState(ControlState state, int borderThickness);

  BCS setBottomBorderColorForState(ControlState state, IColor bottomBorderColor);

  BCS setBottomBorderThicknessForState(ControlState state, int bottomBorderThickness);

  BCS setLeftBorderColorForState(ControlState state, IColor leftBorderColor);

  BCS setLeftBorderThicknessForState(ControlState state, int leftBorderThickness);

  BCS setRightBorderColorForState(ControlState state, IColor rightBorderColor);

  BCS setRightBorderThicknessForState(ControlState state, int rightBorderThickness);

  BCS setTopBorderColorForState(ControlState state, IColor topBorderColor);

  BCS setTopBorderThicknessForState(ControlState state, int topBorderThickness);
}
