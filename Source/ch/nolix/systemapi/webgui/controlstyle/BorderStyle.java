/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link BorderStyle}.
 */
public interface BorderStyle<S extends BorderStyle<S>> {
  /**
   * Sets the given borderColor for the given state to all borders of the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param borderColor
   * @return the current {@link BorderStyle}
   */
  S forStateSetBorderColor(ControlState state, IColor borderColor);

  /**
   * Sets the given borderThickness for the given state to all borders of the
   * current {@link BorderStyle}.
   * 
   * @param state
   * @param borderThickness
   * @return the current {@link BorderStyle}
   */
  S forStateSetBorderThickness(ControlState state, int borderThickness);

  /**
   * Sets the given bottomBorderColor for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param bottomBorderColor
   * @return the current {@link BorderStyle}
   */
  S forStateSetBottomBorderColor(ControlState state, IColor bottomBorderColor);

  /**
   * Sets the given bottomBorderThickness for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param bottomBorderThickness
   * @return the current {@link BorderStyle}
   */
  S forStateSetBottomBorderThickness(ControlState state, int bottomBorderThickness);

  /**
   * Sets the given leftBorderColor for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param leftBorderColor
   * @return the current {@link BorderStyle}
   */
  S forStateSetLeftBorderColor(ControlState state, IColor leftBorderColor);

  /**
   * Sets the given leftBorderThickness for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param leftBorderThickness
   * @return the current {@link BorderStyle}
   */
  S forStateSetLeftBorderThickness(ControlState state, int leftBorderThickness);

  /**
   * Sets the given rightBorderColor for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param rightBorderColor
   * @return the current {@link BorderStyle}
   */
  S forStateSetRightBorderColor(ControlState state, IColor rightBorderColor);

  /**
   * Sets the given rightBorderThickness for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param rightBorderThickness
   * @return the current {@link BorderStyle}
   */
  S forStateSetRightBorderThickness(ControlState state, int rightBorderThickness);

  /**
   * Sets the given topBorderColor for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param topBorderColor
   * @return the current {@link BorderStyle}
   */
  S forStateSetTopBorderColor(ControlState state, IColor topBorderColor);

  /**
   * Sets the given topBorderThickness for the given state to the current
   * {@link BorderStyle}.
   * 
   * @param state
   * @param topBorderThickness
   * @return the current {@link BorderStyle}
   */
  S forStateSetTopBorderThickness(ControlState state, int topBorderThickness);

  /**
   * @param state
   * @return the bottom border color of the current {@link BorderStyle} when it
   *         has the given state
   */
  IColor getBottomBorderColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the bottom border thickness of the current {@link BorderStyle} when
   *         it has the given state
   */
  int getBottomBorderThicknessWhenHasState(ControlState state);

  /**
   * @param state
   * @return the left border color of the current {@link BorderStyle} when it has
   *         the given state
   */
  IColor getLeftBorderColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the left border thickness of the current {@link BorderStyle} when it
   *         has the given state
   */
  int getLeftBorderThicknessWhenHasState(ControlState state);

  /**
   * @param state
   * @return the right border color of the current {@link BorderStyle} when it has
   *         the given state
   */
  IColor getRightBorderColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the right border thickness of the current {@link BorderStyle} when it
   *         has the given state
   */
  int getRightBorderThicknessWhenHasState(ControlState state);

  /**
   * @param state
   * @return the top border color of the current {@link BorderStyle} when it has
   *         the given state
   */
  IColor getTopBorderColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the top border thickness of the current {@link BorderStyle} when it
   *         has the given state
   */
  int getTopBorderThicknessWhenHasState(ControlState state);

  /**
   * Removes the custom border colors of all borders of the current
   * {@link BorderStyle} for all states.
   */
  void removeCustomBorderColors();

  /**
   * Removes the custom border thicknesses of all borders of the current
   * {@link BorderStyle} for all states.
   */
  void removeCustomBorderThicknesses();

  /**
   * Removes the custom bottom border colors of the current {@link BorderStyle}
   * for all states.
   */
  void removeCustomBottomBorderColors();

  /**
   * Removes the custom bottom border thicknesses of the current
   * {@link BorderStyle} for all states.
   */
  void removeCustomBottomBorderThicknesses();

  /**
   * Removes the custom left border colors of the current {@link BorderStyle} for
   * all states.
   */
  void removeCustomLeftBorderColors();

  /**
   * Removes the custom left border thicknesses of the current {@link BorderStyle}
   * for all states.
   */
  void removeCustomLeftBorderThicknesses();

  /**
   * Removes the custom right border colors of the current {@link BorderStyle} for
   * all states.
   */
  void removeCustomRightBorderColors();

  /**
   * Removes the custom right border thicknesses of the current
   * {@link BorderStyle} for all states.
   */
  void removeCustomRightBorderThicknesses();

  /**
   * Removes the custom top border colors of the current {@link BorderStyle} for
   * all states.
   */
  void removeCustomTopBorderColors();

  /**
   * Removes the custom top border thicknesses of the current {@link BorderStyle}
   * for all states.
   */
  void removeCustomTopBorderThicknesses();
}
