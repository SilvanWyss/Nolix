/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public final class ControlFactory {
  private static final ControlFactoryUnit CONTROL_FACTORY = new ControlFactoryUnit();

  private ControlFactory() {
  }

  public static boolean canCreateControlOfType(final String type) {
    return CONTROL_FACTORY.canCreateControlOfType(type);
  }

  public static Control<?, ?> createControlFromSpecification(final INode<?> specification) {
    return CONTROL_FACTORY.createControlFromSpecification(specification);
  }

  public static Control<?, ?> createControlOfType(final String type) {
    return CONTROL_FACTORY.createControlOfType(type);
  }

  public static void registerControlClass(
    final Class<Control<?, ?>> controlClass,
    final @SuppressWarnings("unchecked") Class<Control<?, ?>>... controlClasses) {
    CONTROL_FACTORY.registerControlClass(controlClass, controlClasses);
  }
}
