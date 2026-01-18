/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IBackgroundStyle}.
 */
public interface IBackgroundStyle<S extends IBackgroundStyle<S>> {
  IBackground getBackgroundWhenHasState(ControlState state);

  void removeCustomBackgrounds();

  S forStateSetBackgroundColor(ControlState state, IColor backgroundColor);

  S forStateSetBackgroundColorGradient(ControlState state, IColorGradient backgroundColorGradient);

  S forStateSetBackgroundImage(ControlState state, IImage backgroundImage, ImageApplication imageApplication);

  S forStateSetBackground(ControlState state, IBackground background);
}
