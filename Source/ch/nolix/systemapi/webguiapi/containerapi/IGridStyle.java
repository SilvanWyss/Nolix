package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IGridStyle extends IControlStyle<IGridStyle> {

  int getChildControlMarginWhenHasState(ControlState state);

  IColor getGridColorWhenHasState(ControlState state);

  int getGridThicknessWhenHasState(ControlState state);

  GridType getGridTypeWhenHasState(ControlState state);

  void removeCustomChildControlMargins();

  void removeCustomGridColors();

  void removeCustomGridThicknesses();

  void removeCustomGridTypes();

  IGridStyle setChildControlMarginForState(ControlState state, int childControlMargin);

  IGridStyle setGridColorForState(ControlState state, IColor gridColor);

  IGridStyle setGridThicknessForState(ControlState state, int gridThickness);

  IGridStyle setGridTypeForState(ControlState state, GridType gridType);
}
