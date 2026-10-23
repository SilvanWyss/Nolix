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
  /**
   * @param state
   * @return the child control margin of the current {@link IGridStyle} when it
   *         has the given state
   */
  int getChildControlMarginWhenHasState(ControlState state);

  /**
   * @param state
   * @return the grid color of the current {@link IGridStyle} when it has the
   *         given state
   */
  IColor getGridColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the grid thickness of the current {@link IGridStyle} when it has the
   *         given state
   */
  int getGridThicknessWhenHasState(ControlState state);

  /**
   * @param state
   * @return the {@link GridType} of the current {@link IGridStyle} when it has
   *         the given state
   */
  GridType getGridTypeWhenHasState(ControlState state);

  /**
   * Removes the custom child control margins of the current {@link IGridStyle}
   * for all states.
   */
  void removeCustomChildControlMargins();

  /**
   * Removes the custom grid colors of the current {@link IGridStyle} for all
   * states.
   */
  void removeCustomGridColors();

  /**
   * Removes the custom grid thicknesses of the current {@link IGridStyle} for all
   * states.
   */
  void removeCustomGridThicknesses();

  /**
   * Removes the custom grid types of the current {@link IGridStyle} for all
   * states.
   */
  void removeCustomGridTypes();

  /**
   * Sets the given childControlMargin for the given state to the current
   * {@link IGridStyle}.
   * 
   * @param state
   * @param childControlMargin
   * @return the current {@link IGridStyle}
   */
  IGridStyle setChildControlMarginForState(ControlState state, int childControlMargin);

  /**
   * Sets the given gridColor for the given state to the current
   * {@link IGridStyle}.
   * 
   * @param state
   * @param gridColor
   * @return the current {@link IGridStyle}
   */
  IGridStyle setGridColorForState(ControlState state, IColor gridColor);

  /**
   * Sets the given gridThickness for the given state to the current
   * {@link IGridStyle}.
   * 
   * @param state
   * @param gridThickness
   * @return the current {@link IGridStyle}
   */
  IGridStyle setGridThicknessForState(ControlState state, int gridThickness);

  /**
   * Sets the given gridType for the given state to the current
   * {@link IGridStyle}.
   * 
   * @param state
   * @param gridType
   * @return the current {@link IGridStyle}
   */
  IGridStyle setGridTypeForState(ControlState state, GridType gridType);
}
