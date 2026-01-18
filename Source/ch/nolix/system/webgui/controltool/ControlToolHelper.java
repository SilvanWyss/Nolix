/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controltool;

import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class ControlToolHelper {
  private ControlToolHelper() {
  }

  public static void fillUpChildControlsOfControlIntoListRecursively(
    final IControl<?, ?> control,
    final IArrayList<IControl<?, ?>> list) {
    final var childControls = control.getStoredChildControls();

    for (final var c : childControls) {
      list.addAtEnd(c);
      fillUpChildControlsOfControlIntoListRecursively(c, list);
    }
  }
}
