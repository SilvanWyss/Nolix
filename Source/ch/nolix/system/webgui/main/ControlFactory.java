package ch.nolix.system.webgui.main;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.structurecontrol.reflectiontool.GlobalReflectionTool;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

public final class ControlFactory {

  private final LinkedList<Class<Control<?, ?>>> controlClasses = LinkedList.createEmpty();

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

    return GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(controlClass);
  }

  public void registerControlClass(
    final Class<Control<?, ?>> controlClass,
    final @SuppressWarnings("unchecked") Class<Control<?, ?>>... controlClasses) {

    final var allControlClasses = ContainerView.forElementAndArray(controlClass, controlClasses);

    for (final var cc : allControlClasses) {

      assertDoesNotContainControlClassWithName(cc.getSimpleName());

      this.controlClasses.addAtEnd(cc);
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
    return controlClasses.containsAny(cc -> cc.getSimpleName().equals(name));
  }

  private Class<Control<?, ?>> getControlClassByName(final String name) {
    return controlClasses.getStoredFirst(cc -> cc.getSimpleName().equals(name));
  }
}
