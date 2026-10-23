/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.font.Font;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ControlBaseStyle}.
 */
public interface ControlBaseStyle<S extends ControlBaseStyle<S>> extends MultiStateConfiguration<S, ControlState> {

  /**
   * @param state
   * @return true if the current {@link ControlBaseStyle} defines a text line
   *         decoration for the given state, false otherwise
   */
  boolean definesTextLineDecorationForState(ControlState state);

  /**
   * Sets the given boldTextFlag for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param boldTextFlag
   * @return the current {@link ControlBaseStyle}
   */
  S forStateSetBoldTextFlag(ControlState state, boolean boldTextFlag);

  /**
   * Sets the given font for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param font
   * @return the current {@link ControlBaseStyle}
   */
  S forStateSetFont(ControlState state, Font font);

  /**
   * Sets the given opacity for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param opacity
   * @return the current {@link ControlBaseStyle}
   * @throws RuntimeException if the given opacity is not between 0.0 and 1.0
   */
  S forStateSetOpacity(ControlState state, double opacity);

  /**
   * Sets the given textColor for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param textColor
   * @return the current {@link ControlBaseStyle}
   */
  S forStateSetTextColor(ControlState state, IColor textColor);

  /**
   * Sets the given textLineDecoration for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param textLineDecoration
   * @return the current {@link ControlBaseStyle}
   */
  S forStateSetTextLineDecoration(ControlState state, LineDecoration textLineDecoration);

  /**
   * Sets the given textSize for the given state to the current
   * {@link ControlBaseStyle}.
   * 
   * @param state
   * @param textSize
   * @return the current {@link ControlBaseStyle}
   */
  S forStateSetTextSize(ControlState state, int textSize);

  /**
   * @param state
   * @return the bold text flag of the current {@link ControlBaseStyle} when it
   *         has the given state
   */
  boolean getBoldTextFlagWhenHasState(ControlState state); // NOSONAR: This method returns a flag as a boolean.

  /**
   * @param state
   * @return the font of the current {@link ControlBaseStyle} when it has the
   *         given state
   */
  Font getFontWhenHasState(ControlState state);

  /**
   * @param state
   * @return the opacity of the current {@link ControlBaseStyle} when it has the
   *         given state
   */
  double getOpacityWhenHasState(ControlState state);

  /**
   * @param state
   * @return the text color of the current {@link ControlBaseStyle} when it has
   *         the given state
   */
  IColor getTextColorWhenHasState(ControlState state);

  /**
   * @param state
   * @return the text line decoration of the current {@link ControlBaseStyle} when
   *         it has the given state
   */
  LineDecoration getTextLineDecorationWhenHasState(ControlState state);

  /**
   * @param state
   * @return the text size of the current {@link ControlBaseStyle} when it has the
   *         given state
   */
  int getTextSizeWhenHasState(ControlState state);

  /**
   * Removes the custom bold text flags of the current {@link ControlBaseStyle}
   * for all states.
   */
  void removeCustomBoldTextFlags();

  /**
   * Removes the custom fonts of the current {@link ControlBaseStyle} for all
   * states.
   */
  void removeCustomFonts();

  /**
   * Removes the custom opacities of the current {@link ControlBaseStyle} for all
   * states.
   */
  void removeCustomOpacities();

  /**
   * Removes the custom text colors of the current {@link ControlBaseStyle} for
   * all states.
   */
  void removeCustomTextColors();

  /**
   * Removes the custom text line decorations of the current
   * {@link ControlBaseStyle} for all states.
   */
  void removeCustomTextLineDecorations();

  /**
   * Removes the custom text sizes of the current {@link ControlBaseStyle} for all
   * states.
   */
  void removeCustomTextSizes();
}
