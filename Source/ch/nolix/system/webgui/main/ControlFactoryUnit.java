package ch.nolix.system.webgui.main;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.coreapi.document.node.INode;

public final class ControlFactoryUnit {
  private final LinkedList<Class<Control<?, ?>>> memberControlClasses = LinkedList.createEmpty();

  public boolean canCreateControlOfType(final String type) {
    return containsControlClassWithName(type);
  }

  public Control<?, ?> createControlFromSpecification(final INode<?> specification) {
    final var control = createControlOfType(specification.getHeader());

    control.resetFromSpecification(specification);

    return control;
  }

  public Control<?, ?> createControlOfType(final String type) {
    final var controlClass = getControlClassByName(type);

    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(controlClass);
  }

  public void registerControlClass(
    final Class<Control<?, ?>> controlClass,
    final @SuppressWarnings("unchecked") Class<Control<?, ?>>... controlClasses) {
    final var allControlClasses = ContainerView.forElementAndArray(controlClass, controlClasses);

    for (final var c : allControlClasses) {
      assertDoesNotContainControlClassWithName(c.getSimpleName());

      memberControlClasses.addAtEnd(c);
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

  private Class<Control<?, ?>> getControlClassByName(final String name) {
    return memberControlClasses.getStoredFirst(cc -> cc.getSimpleName().equals(name));
  }
}
