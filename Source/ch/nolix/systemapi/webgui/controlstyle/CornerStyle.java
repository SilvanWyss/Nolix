/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link CornerStyle}.
 */
public interface CornerStyle<S extends CornerStyle<S>> {
  /**
   * Sets the given cornerRadius for the given state to the current
   * {@link CornerStyle}.
   * 
   * @param state
   * @param cornerRadius
   * @return the current {@link CornerStyle}
   */
  S forStateSetCornerRadius(ControlState state, int cornerRadius);

  /**
   * @param state
   * @return the corner radius of the current {@link CornerStyle} when it has the
   *         given state
   */
  int getCornerRadiusWhenHasState(ControlState state);

  /**
   * Removes the custom corner radiuses of the current {@link CornerStyle} for all
   * states.
   */
  void removeCustomCornerRadiuses();
}
