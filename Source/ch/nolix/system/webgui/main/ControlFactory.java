/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.webgui.main.Control;

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

  public static Control<?, ?> createControlFromSpecification(final Node<?> specification) {
    return CONTROL_FACTORY.createControlFromSpecification(specification);
  }

  public static Control<?, ?> createControlOfType(final String type) {
    return CONTROL_FACTORY.createControlOfType(type);
  }

  public static void registerControlClass(final Class<? extends Control<?, ?>> controlClass) {
    CONTROL_FACTORY.registerControlClass(controlClass);
  }

  public static void registerControlClasses(
    final @SuppressWarnings("unchecked") Class<AbstractControl<?, ?>>... controlClasses) {
    CONTROL_FACTORY.registerControlClasses(controlClasses);
  }
}
