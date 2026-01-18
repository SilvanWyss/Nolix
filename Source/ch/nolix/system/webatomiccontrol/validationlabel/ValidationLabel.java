/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.validationlabel;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabelStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabel
extends Control<IValidationLabel, IValidationLabelStyle>
implements IValidationLabel {
  private static final String ERROR_HEADER = PascalCaseVariableCatalog.ERROR;

  private final MutableOptionalValue<Throwable> memberError = new MutableOptionalValue<>(
    ERROR_HEADER,
    this::showError,
    s -> GeneralException.withErrorMessage(s.getHeader()),
    e -> Node.withHeader(e.getMessage()));

  public ValidationLabel() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    setMinWidth(500);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberError.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return memberError.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberError.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runHtmlEvent(String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ValidationLabel setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showError(final Throwable error) {
    memberError.setValue(error);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ValidationLabelStyle createStyle() {
    return new ValidationLabelStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IValidationLabel, IValidationLabelStyle> getCssBuilder() {
    return new ValidationLabelCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IValidationLabel> getHtmlBuilder() {
    return new ValidationLabelHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetControl() {
    clear();

    getStoredStyle().forStateSetTextColor(ControlState.BASE, X11ColorCatalog.RED);
  }
}
