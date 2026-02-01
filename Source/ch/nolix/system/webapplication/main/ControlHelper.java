/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import java.util.Optional;

import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class ControlHelper {
  private ControlHelper() {
  }

  public static Optional<IComponent> getOptionalStoredParentComponentOfControl(final IControl<?, ?> control) {
    if (control.isLinkedToAnObject()
    && control.getStoredLinkedObjects().getStoredFirst() instanceof final IComponent component) {
      return Optional.of(component);
    }

    if (control.belongsToControl()) {
      return getOptionalStoredParentComponentOfControl(control.getStoredParentControl());
    }

    return Optional.empty();
  }
}
