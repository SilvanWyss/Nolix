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

  /**
   * @param type
   * @return true if a control of the given type can be created, false otherwise
   */
  public static boolean canCreateControlOfType(final String type) {
    return CONTROL_FACTORY.canCreateControlOfType(type);
  }

  /**
   * @param specification
   * @return a new control from the given specification
   * @throws RuntimeException if a control cannot be created from the given
   *                          specification
   */
  public static Control<?, ?> createControlFromSpecification(final Node<?> specification) {
    return CONTROL_FACTORY.createControlFromSpecification(specification);
  }

  /**
   * @param type
   * @return a new control of the given type
   * @throws RuntimeException if a control of the given type cannot be created
   */
  public static Control<?, ?> createControlOfType(final String type) {
    return CONTROL_FACTORY.createControlOfType(type);
  }

  /**
   * Registers the given controlClass.
   * 
   * @param controlClass
   */
  public static void registerControlClass(final Class<? extends Control<?, ?>> controlClass) {
    CONTROL_FACTORY.registerControlClass(controlClass);
  }

  /**
   * Registers the given controlClasses.
   * 
   * @param controlClasses
   */
  @SuppressWarnings("unchecked")
  public static void registerControlClasses(
    final Class<AbstractControl<?, ?>>... controlClasses) {
    CONTROL_FACTORY.registerControlClasses(controlClasses);
  }
}
