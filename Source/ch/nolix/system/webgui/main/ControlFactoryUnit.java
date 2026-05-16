/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class ControlFactoryUnit {
  private final LinkedList<Class<? extends IControl<?, ?>>> memberControlClasses = LinkedList.createEmpty();

  public boolean canCreateControlOfType(final String type) {
    return containsControlClassWithName(type);
  }

  public IControl<?, ?> createControlFromSpecification(final INode<?> specification) {
    final var control = createControlOfType(specification.getHeader());

    control.resetFromSpecification(specification);

    return control;
  }

  public IControl<?, ?> createControlOfType(final String type) {
    final var controlClass = getControlClassByName(type);

    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(controlClass);
  }

  public void registerControlClass(
    final Class<? extends IControl<?, ?>> controlClass) {
    assertDoesNotContainControlClassWithName(controlClass.getSimpleName());

    memberControlClasses.addAtEnd(controlClass);
  }

  public void registerControlClasses(final @SuppressWarnings("unchecked") Class<AbstractControl<?, ?>>... controlClasses) {
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
    return memberControlClasses.containsAny(cc -> cc.getSimpleName().equals(name));
  }

  private Class<? extends IControl<?, ?>> getControlClassByName(final String name) {
    return memberControlClasses.getStoredFirst(cc -> cc.getSimpleName().equals(name));
  }
}
