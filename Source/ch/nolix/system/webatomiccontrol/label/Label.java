/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.label;

import java.util.Optional;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webatomiccontrol.label.ILabel;
import ch.nolix.systemapi.webatomiccontrol.label.ILabelStyle;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class Label extends Control<ILabel, ILabelStyle> implements ILabel {
  public static final String DEFAULT_TEXT = StringCatalog.MINUS;

  private static final String ROLE_HEADER = PascalCaseVariableCatalog.ROLE;

  private static final String TEXT_HEADER = PascalCaseVariableCatalog.TEXT;

  private static final LabelHtmlBuilder HTML_BUILDER = new LabelHtmlBuilder();

  private static final LabelCssBuilder CSS_BUILDER = new LabelCssBuilder();

  private final OptionalValue<LabelRole> memberRole = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    ROLE_HEADER,
    this::setRole,
    LabelRole::fromSpecification,
    Node::fromEnum);

  private final Value<String> text = //
  Value.forStringWithNameAndDefaultValueAndSetter(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  public Label() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();
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
  public LabelRole getRole() {
    return memberRole.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return text.getStoredValue();
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
  public boolean hasRole() {
    return memberRole.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
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
  public void removeRole() {
    memberRole.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILabel setRole(final LabelRole role) {
    memberRole.setValue(role);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILabel setText(final String text) {
    Validator.assertThat(text).thatIsNamed(LowerCaseVariableCatalog.TEXT).isNotNull();

    this.text.setValue(text);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILabel setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LabelStyle createStyle() {
    return new LabelStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<ILabel, ILabelStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<ILabel> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetControl() {
    removeRole();
    setText(DEFAULT_TEXT);

    setCursorIcon(CursorIcon.EDIT);
  }
}
