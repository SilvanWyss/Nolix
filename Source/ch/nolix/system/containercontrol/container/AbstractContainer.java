/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.container;

import java.util.Optional;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.containercontrol.container.ContainerRole;
import ch.nolix.systemapi.containercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractContainer}
 * @param <S> the type of the {@link IControlStyle}s of a
 *            {@link AbstractContainer}
 */
public abstract class AbstractContainer<C extends IContainer<C, S>, S extends IControlStyle<S>>
extends AbstractControl<C, S> implements IContainer<C, S> {
  private static final String ROLE_HEADER = PascalCaseVariableCatalog.ROLE;

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
   * @param <C2>         the type of the given childControl
   * @param <S2>         the type of the {@link IControlStyle} of the given
   *                     childControl
   * @param childControl
   */
  protected final <C2 extends IControl<C2, S2>, S2 extends IControlStyle<S2>> void registerChildControl(
    final C2 childControl) {
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
}
