/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.base.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link SizeStyle}.
 */
public interface SizeStyle<S extends SizeStyle<S>> {
  /**
   * @param state
   * @return true if the current {@link SizeStyle} defines a height for the given
   *         state, false otherwise
   */
  boolean definesHeightForState(ControlState state);

  /**
   * @param state
   * @return true if the current {@link SizeStyle} defines a width for the given
   *         state, false otherwise
   */
  boolean definesWidthForState(ControlState state);

  /**
   * Sets the given height for the given state to the current {@link SizeStyle}.
   * 
   * @param state
   * @param height
   * @return the current {@link SizeStyle}
   */
  S forStateSetHeight(ControlState state, int height);

  /**
   * Sets the given heightInPercentOfViewAreaHeight of the height of the view area
   * as height for the given state to the current {@link SizeStyle}.
   * 
   * @param state
   * @param heightInPercentOfViewAreaHeight
   * @return the current {@link SizeStyle}
   */
  S forStateSetHeightInPercentOfViewArea(ControlState state, double heightInPercentOfViewAreaHeight);

  /**
   * Sets the given width for the given state to the current {@link SizeStyle}.
   * 
   * @param state
   * @param width
   * @return the current {@link SizeStyle}
   */
  S forStateSetWidth(ControlState state, int width);

  /**
   * Sets the given widthInPercentOfViewAreaWidth of the width of the view area as
   * width for the given state to the current {@link SizeStyle}.
   * 
   * @param state
   * @param widthInPercentOfViewAreaWidth
   * @return the current {@link SizeStyle}
   */
  S forStateSetWidthInPercentOfViewAreaWidth(ControlState state, double widthInPercentOfViewAreaWidth);

  /**
   * @param state
   * @return the height of the current {@link SizeStyle} for the given state
   */
  IAbsoluteOrRelativeInt getHeightForState(ControlState state);

  /**
   * @param state
   * @return the width of the current {@link SizeStyle} for the given state
   */
  IAbsoluteOrRelativeInt getWidthForState(ControlState state);

  /**
   * Removes the custom heights of the current {@link SizeStyle} for all states.
   */
  void removeCustomHeights();

  /**
   * Removes the custom widths of the current {@link SizeStyle} for all states.
   */
  void removeCustomWidths();
}
