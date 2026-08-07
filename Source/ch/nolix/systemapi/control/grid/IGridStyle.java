/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.grid;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public interface IGridStyle extends ControlStyle<IGridStyle> {
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
