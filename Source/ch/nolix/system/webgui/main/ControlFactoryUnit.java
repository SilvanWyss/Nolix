/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class ControlFactoryUnit {
  private final LinkedList<Class<? extends Control<?, ?>>> memberControlClasses = LinkedList.createEmpty();

  public boolean canCreateControlOfType(final String type) {
    return containsControlClassWithName(type);
  }

  public Control<?, ?> createControlFromSpecification(final Node<?> specification) {
    final var control = createControlOfType(specification.getHeader());

    control.resetFromSpecification(specification);

    return control;
  }

  public Control<?, ?> createControlOfType(final String type) {
    final var controlClass = getControlClassByName(type);

    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(controlClass);
  }

  public void registerControlClass(
    final Class<? extends Control<?, ?>> controlClass) {
    assertDoesNotContainControlClassWithName(controlClass.getSimpleName());

    memberControlClasses.addAtEnd(controlClass);
  }

  public void registerControlClasses(
    final @SuppressWarnings("unchecked") Class<AbstractControl<?, ?>>... controlClasses) {
    for (final var c : controlClasses) {
      registerControlClass(c);
    }
  }

  private void assertDoesNotContainControlClassWithName(final String name) {
    if (containsControlClassWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already a Control class with the name '" + name + "'");
    }
  }

  private boolean containsControlClassWithName(final String name) {
    return memberControlClasses.containsMatching(cc -> cc.getSimpleName().equals(name));
  }

  private Class<? extends Control<?, ?>> getControlClassByName(final String name) {
    return memberControlClasses.getStoredFirst(cc -> cc.getSimpleName().equals(name));
  }
}
