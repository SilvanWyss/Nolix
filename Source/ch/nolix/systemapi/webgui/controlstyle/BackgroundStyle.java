/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.guiproperty.ImageApplication;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link BackgroundStyle}.
 */
public interface BackgroundStyle<S extends BackgroundStyle<S>> {
  /**
   * Sets the given background for the given state to the current
   * {@link BackgroundStyle}.
   * 
   * @param state
   * @param background
   * @return the current {@link BackgroundStyle}
   */
  S forStateSetBackground(ControlState state, IBackground background);

  /**
   * Sets the given backgroundColor for the given state to the current
   * {@link BackgroundStyle}.
   * 
   * @param state
   * @param backgroundColor
   * @return the current {@link BackgroundStyle}
   */
  S forStateSetBackgroundColor(ControlState state, IColor backgroundColor);

  /**
   * Sets the given backgroundColorGradient for the given state to the current
   * {@link BackgroundStyle}.
   * 
   * @param state
   * @param backgroundColorGradient
   * @return the current {@link BackgroundStyle}
   */
  S forStateSetBackgroundColorGradient(ControlState state, IColorGradient backgroundColorGradient);

  /**
   * Sets the given backgroundImage with the given imageApplication for the given
   * state to the current {@link BackgroundStyle}.
   * 
   * @param state
   * @param backgroundImage
   * @param imageApplication
   * @return the current {@link BackgroundStyle}
   */
  S forStateSetBackgroundImage(ControlState state, Image backgroundImage, ImageApplication imageApplication);

  /**
   * @param state
   * @return the background of the current {@link BackgroundStyle} when it has the
   *         given state
   */
  IBackground getBackgroundWhenHasState(ControlState state);

  /**
   * Removes the custom backgrounds of the current {@link BackgroundStyle} for all
   * states.
   */
  void removeCustomBackgrounds();
}
