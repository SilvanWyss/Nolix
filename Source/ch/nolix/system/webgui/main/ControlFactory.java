//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public final class ControlFactory {

  //multi-attribute
  private final LinkedList<Class<Control<?, ?>>> controlClasses = new LinkedList<>();

  //method
  public boolean canCreateControlOfType(final String type) {
    return containsControlClassWithName(type);
  }

  //method
  public Control<?, ?> createControlFromSpecification(final INode<?> specification) {

    final var control = createControlOfType(specification.getHeader());

    control.resetFromSpecification(specification);

    return control;
  }

  //method
  public Control<?, ?> createControlOfType(final String type) {

    final var controlClass = getControlClassByName(type);

    return GlobalClassHelper.createInstanceFromDefaultConstructorOf(controlClass);
  }

  //method
  public void registerControlClass(
    final Class<Control<?, ?>> controlClass,
    final @SuppressWarnings("unchecked") Class<Control<?, ?>>... controlClasses) {

    final var allControlClasses = ReadContainer.forElement(controlClass, controlClasses);

    for (final var cc : allControlClasses) {

      assertDoesNotContainControlClassWithName(cc.getSimpleName());

      this.controlClasses.addAtEnd(cc);
    }
  }

  //method
  private void assertDoesNotContainControlClassWithName(final String name) {
    if (containsControlClassWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already a Control class with the name '" + name + "'");
    }
  }

  //method
  private boolean containsControlClassWithName(final String name) {
    return controlClasses.containsAny(cc -> cc.getSimpleName().equals(name));
  }

  //method
  private Class<Control<?, ?>> getControlClassByName(final String name) {
    return controlClasses.getStoredFirst(cc -> cc.getSimpleName().equals(name));
  }
}
