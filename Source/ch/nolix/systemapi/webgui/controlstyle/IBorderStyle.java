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

  S forStateSetBorderColor(ControlState state, IColor borderColor);

  S forStateSetBorderThickness(ControlState state, int borderThickness);

  S forStateSetBottomBorderColor(ControlState state, IColor bottomBorderColor);

  S forStateSetBottomBorderThickness(ControlState state, int bottomBorderThickness);

  S forStateSetLeftBorderColor(ControlState state, IColor leftBorderColor);

  S forStateSetLeftBorderThickness(ControlState state, int leftBorderThickness);

  S forStateSetRightBorderColor(ControlState state, IColor rightBorderColor);

  S forStateSetRightBorderThickness(ControlState state, int rightBorderThickness);

  S forStateSetTopBorderColor(ControlState state, IColor topBorderColor);

  S forStateSetTopBorderThickness(ControlState state, int topBorderThickness);
}
