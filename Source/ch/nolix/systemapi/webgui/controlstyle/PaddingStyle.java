/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.base.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link PaddingStyle}.
 */
public interface PaddingStyle<S extends PaddingStyle<S>> {
  /**
   * Sets the given bottomPadding for the given state to the current
   * {@link PaddingStyle}.
   * 
   * @param state
   * @param bottomPadding
   * @return the current {@link PaddingStyle}
   */
  S forStateSetBottomPadding(ControlState state, int bottomPadding);

  /**
   * Sets the given leftPadding for the given state to the current
   * {@link PaddingStyle}.
   * 
   * @param state
   * @param leftPadding
   * @return the current {@link PaddingStyle}
   */
  S forStateSetLeftPadding(ControlState state, int leftPadding);

  /**
   * Sets the given padding for the given state to all sides of the current
   * {@link PaddingStyle}.
   * 
   * @param state
   * @param padding
   * @return the current {@link PaddingStyle}
   */
  S forStateSetPadding(ControlState state, int padding);

  /**
   * Sets the given rightPadding for the given state to the current
   * {@link PaddingStyle}.
   * 
   * @param state
   * @param rightPadding
   * @return the current {@link PaddingStyle}
   */
  S forStateSetRightPadding(ControlState state, int rightPadding);

  /**
   * Sets the given topPadding for the given state to the current
   * {@link PaddingStyle}.
   * 
   * @param state
   * @param topPadding
   * @return the current {@link PaddingStyle}
   */
  S forStateSetTopPadding(ControlState state, int topPadding);

  /**
   * @param state
   * @return the bottom padding of the current {@link PaddingStyle} when it has
   *         the given state
   */
  IAbsoluteOrRelativeInt getBottomPaddingWhenHasState(ControlState state);

  /**
   * @param state
   * @return the left padding of the current {@link PaddingStyle} when it has the
   *         given state
   */
  IAbsoluteOrRelativeInt getLeftPaddingWhenHasState(ControlState state);

  /**
   * @param state
   * @return the right padding of the current {@link PaddingStyle} when it has the
   *         given state
   */
  IAbsoluteOrRelativeInt getRightPaddingWhenHasState(ControlState state);

  /**
   * @param state
   * @return the top padding of the current {@link PaddingStyle} when it has the
   *         given state
   */
  IAbsoluteOrRelativeInt getTopPaddingWhenHasState(ControlState state);

  /**
   * Removes the custom bottom paddings of the current {@link PaddingStyle} for
   * all states.
   */
  void removeCustomBottomPaddings();

  /**
   * Removes the custom left paddings of the current {@link PaddingStyle} for all
   * states.
   */
  void removeCustomLeftPaddings();

  /**
   * Removes the custom paddings of all sides of the current {@link PaddingStyle}
   * for all states.
   */
  void removeCustomPaddings();

  /**
   * Removes the custom right paddings of the current {@link PaddingStyle} for all
   * states.
   */
  void removeCustomRightPaddings();

  /**
   * Removes the custom top paddings of the current {@link PaddingStyle} for all
   * states.
   */
  void removeCustomTopPaddings();
}
