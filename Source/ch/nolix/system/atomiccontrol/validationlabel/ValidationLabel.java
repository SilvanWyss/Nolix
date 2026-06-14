/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.validationlabel;

import java.util.Optional;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.systemapi.atomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemapi.atomiccontrol.validationlabel.IValidationLabelStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabel
extends AbstractControl<IValidationLabel, IValidationLabelStyle>
implements IValidationLabel {
  private static final String ERROR_HEADER = PascalCaseVariableCatalog.ERROR;

  private final OptionalValue<Throwable> memberError = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
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
    return memberError.getStoredValue();
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
  public IWellOrderContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<IControl<?, ?>> getStoredStructureControls() {
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
