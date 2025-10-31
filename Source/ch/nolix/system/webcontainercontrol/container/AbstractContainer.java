package ch.nolix.system.webcontainercontrol.container;

import java.util.Optional;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webcontainercontrol.container.ContainerRole;
import ch.nolix.systemapi.webcontainercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;

public abstract class AbstractContainer<C extends IContainer<C, S>, S extends IControlStyle<S>>
extends Control<C, S> implements IContainer<C, S> {
  private static final String ROLE_HEADER = PascalCaseVariableCatalog.ROLE;

  private final MutableOptionalValue<ContainerRole> role = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    ContainerRole::fromSpecification,
    Node::fromEnum);

  @Override
  public final Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public final ContainerRole getRole() {
    return role.getValue();
  }

  @Override
  public final String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  @Override
  public final boolean hasRole() {
    return role.containsAny();
  }

  @Override
  public final boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  @Override
  public final void removeRole() {
    role.clear();
  }

  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public final C setRole(final ContainerRole role) {
    this.role.setValue(role);

    return asConcrete();
  }

  @Override
  public final C setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  protected abstract void resetContainer();

  @Override
  protected final void resetControl() {
    removeRole();
    clear();

    resetContainer();
  }
}
