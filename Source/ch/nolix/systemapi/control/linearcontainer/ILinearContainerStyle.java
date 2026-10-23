/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.linearcontainer;

import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ILinearContainerStyle}.
 */
public interface ILinearContainerStyle<S extends ILinearContainerStyle<S>> extends ControlStyle<S> {
  /**
   * @param state
   * @return the child control margin of the current {@link ILinearContainerStyle}
   *         when it has the given state
   */
  int getChildControlMarginWhenHasState(ControlState state);

  /**
   * Removes the custom child control margins of the current
   * {@link ILinearContainerStyle} for all states.
   */
  void removeCustomChildControlMargins();

  /**
   * Sets the given childControlMargin for the given state to the current
   * {@link ILinearContainerStyle}.
   * 
   * @param state
   * @param childControlMargin
   * @return the current {@link ILinearContainerStyle}
   */
  S setChildControlMarginForState(ControlState state, int childControlMargin);
}
