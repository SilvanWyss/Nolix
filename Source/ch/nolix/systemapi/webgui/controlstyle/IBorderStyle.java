package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface IBorderStyle<S extends IBorderStyle<S>> {

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

  S setBorderColorForState(ControlState state, IColor borderColor);

  S setBorderThicknessForState(ControlState state, int borderThickness);

  S setBottomBorderColorForState(ControlState state, IColor bottomBorderColor);

  S setBottomBorderThicknessForState(ControlState state, int bottomBorderThickness);

  S setLeftBorderColorForState(ControlState state, IColor leftBorderColor);

  S setLeftBorderThicknessForState(ControlState state, int leftBorderThickness);

  S setRightBorderColorForState(ControlState state, IColor rightBorderColor);

  S setRightBorderThicknessForState(ControlState state, int rightBorderThickness);

  S setTopBorderColorForState(ControlState state, IColor topBorderColor);

  S setTopBorderThicknessForState(ControlState state, int topBorderThickness);
}
