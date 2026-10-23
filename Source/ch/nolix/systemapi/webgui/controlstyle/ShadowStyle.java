/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.base.ICornerShadow;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ShadowStyle}.
 */
public interface ShadowStyle<S extends ShadowStyle<S>> {
  /**
   * Sets the given cornerShadow for the given state to the current
   * {@link ShadowStyle}.
   * 
   * @param state
   * @param cornerShadow
   * @return the current {@link ShadowStyle}
   */
  S forStateSetCornerShadow(ControlState state, ICornerShadow cornerShadow);

  /**
   * Sets the given cornerShadows for the given state to the current
   * {@link ShadowStyle}.
   * 
   * @param state
   * @param cornerShadows
   * @return the current {@link ShadowStyle}
   */
  S forStateSetCornerShadows(ControlState state, ExtendedIterable<? extends ICornerShadow> cornerShadows);

  /**
   * Sets the given cornerShadows for the given state to the current
   * {@link ShadowStyle}.
   * 
   * @param state
   * @param cornerShadows
   * @return the current {@link ShadowStyle}
   */
  S forStateSetCornerShadows(ControlState state, ICornerShadow... cornerShadows);

  /**
   * @param state
   * @return the corner shadows of the current {@link ShadowStyle} when it has the
   *         given state
   */
  ExtendedIterable<? extends ICornerShadow> getCornerShadowsWhenHasState(ControlState state);

  /**
   * Removes the custom corner shadows of the current {@link ShadowStyle} for all
   * states.
   */
  void removeCustomCornerShadows();
}
