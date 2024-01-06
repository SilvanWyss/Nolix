//package declaration
package ch.nolix.system.webgui.basecontainer;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//class
public abstract class Container<C extends IContainer<C, ECS>, ECS extends IControlStyle<ECS>>
extends Control<C, ECS> implements IContainer<C, ECS> {

  //constant
  private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;

  //attribute
  private final MutableOptionalValue<ContainerRole> role = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    ContainerRole::fromSpecification,
    Node::fromEnum);

  //method
  @Override
  public final Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  //method
  @Override
  public final ContainerRole getRole() {
    return role.getValue();
  }

  //method
  @Override
  public final String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  //method
  @Override
  public final boolean hasRole() {
    return role.containsAny();
  }

  //method
  @Override
  public final boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  //method
  @Override
  public final void removeRole() {
    role.clear();
  }

  //method
  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  //method
  @Override
  public final C setRole(final ContainerRole role) {

    this.role.setValue(role);

    return asConcrete();
  }

  //method
  @Override
  public final C setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  //method declaration
  protected abstract void resetContainer();

  //method
  @Override
  protected final void resetControl() {

    removeRole();
    clear();

    resetContainer();
  }
}
