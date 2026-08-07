/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.container;

import java.util.Optional;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.control.container.Container;
import ch.nolix.systemapi.control.container.ContainerRole;
import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractContainer}
 * @param <S> the type of the {@link ControlStyle}s of a
 *            {@link AbstractContainer}
 */
public abstract class AbstractContainer<C extends Container<C, S>, S extends ControlStyle<S>>
extends AbstractControl<C, S> implements Container<C, S> {
  private static final String ROLE_HEADER = PascalCaseVariableNameCatalog.ROLE;

  private final OptionalValue<ContainerRole> memberRole = //
  OptionalValue.forEnumWithNameAndSetter(ContainerRole.class, ROLE_HEADER, this::setRole);

  /**
   * {@inheritDoc}
   */
  @Override
  public final Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ContainerRole getRole() {
    return memberRole.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasRole() {
    return memberRole.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasRole(final String role) {
    return hasRole() && getRole().toString().equals(role);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeRole() {
    memberRole.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setRole(final ContainerRole role) {
    memberRole.setValue(role);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * Registers the given childControl at the current {@link AbstractContainer}.
   * 
   * @param childControl
   */
  protected final void registerChildControl(final Control<?, ?> childControl) {
    final var controlParent = ControlParent.forControl(this);

    childControl.internalSetControlParent(controlParent);
  }

  /**
   * Resets all parts of the sub classes of the current {@link AbstractContainer}.
   */
  protected abstract void resetContainer();

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetControl() {
    removeRole();
    clear();
    resetContainer();
  }

  /**
   * Unregisters the given childControl from the current
   * {@link AbstractContainer}.
   * 
   * @param childControl
   * @throws RuntimeException if the given childControl does not belong to the
   *                          current {@link AbstractContainer}.
   */
  protected final void unregisterChildControl(
    final Control<?, ?> childControl) {
    if (childControl == null || !childControl.belongsToControl() || childControl.getStoredParentControl() != this) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        childControl,
        "child control",
        "does not belong to the current Control");
    }

    childControl.internalRemoveControlParent();
  }
}
