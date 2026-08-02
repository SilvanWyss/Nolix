/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import java.util.Optional;

import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class ControlHelper {
  private ControlHelper() {
  }

  public static Optional<IComponent> getOptionalStoredParentComponentOfControl(final Control<?, ?> control) {
    if (control.belongsToControl()) {
      final var parentControl = control.getStoredParentControl();

      if (parentControl instanceof final IComponent component) {
        return Optional.of(component);
      }

      return getOptionalStoredParentComponentOfControl(parentControl);
    }

    return Optional.empty();
  }
}
