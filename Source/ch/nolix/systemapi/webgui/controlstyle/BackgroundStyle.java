/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link BackgroundStyle}.
 */
public interface BackgroundStyle<S extends BackgroundStyle<S>> {
  IBackground getBackgroundWhenHasState(ControlState state);

  void removeCustomBackgrounds();

  S forStateSetBackgroundColor(ControlState state, IColor backgroundColor);

  S forStateSetBackgroundColorGradient(ControlState state, IColorGradient backgroundColorGradient);

  S forStateSetBackgroundImage(ControlState state, Image backgroundImage, ImageApplication imageApplication);

  S forStateSetBackground(ControlState state, IBackground background);
}
