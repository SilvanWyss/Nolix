/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link ICornerStyle}.
 */
public interface ICornerStyle<S extends ICornerStyle<S>> {
  int getCornerRadiusWhenHasState(ControlState state);

  S forStateSetCornerRadius(ControlState state, int cornerRadius);

  void removeCustomCornerRadiuses();
}
