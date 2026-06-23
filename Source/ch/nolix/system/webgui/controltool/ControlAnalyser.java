/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controltool;

import ch.nolix.systemapi.webgui.controltool.IControlAnalyser;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class ControlAnalyser implements IControlAnalyser {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean firstControlContainsSecondControl(
    final Control<?, ?> firstControl,
    final Control<?, ?> secondControl) {
    return //
    firstControl != null
    && firstControlContainsSecondControlWhenFirstControlIsNotNull(firstControl, secondControl);
  }

  private boolean firstControlContainsSecondControlWhenFirstControlIsNotNull(
    final Control<?, ?> firstControl,
    final Control<?, ?> secondControl) {
    for (final var c : firstControl.getStoredChildControls()) {
      if (c == secondControl || firstControlContainsSecondControl(c, secondControl)) {
        return true;
      }
    }

    return false;
  }
}
