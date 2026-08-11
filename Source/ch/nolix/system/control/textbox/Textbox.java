/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.textbox;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.systemapi.control.textbox.ITextbox;
import ch.nolix.systemapi.control.textbox.ITextboxStyle;
import ch.nolix.systemapi.control.textbox.TextMode;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class Textbox extends AbstractControl<ITextbox, ITextboxStyle> implements ITextbox {
  public static final String DEFAULT_TEXT = StringCatalog.EMPTY_STRING;

  public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;

  private static final String TEXT_HEADER = PascalCaseVariableNameCatalog.TEXT;

  private static final String TEXT_MODE_HEADER = "TextMode";

  private static final TextboxHtmlBuilder HTML_BUILDER = new TextboxHtmlBuilder();

  private static final TextboxCssBuilder CSS_BUILDER = new TextboxCssBuilder();

  private final ValueProperty<String> memberText = //
  ValueProperty.forStringWithNameAndDefaultValueAndSetter(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  private ValueProperty<TextMode> textMode = //
  ValueProperty.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    TEXT_MODE_HEADER,
    DEFAULT_TEXT_MODE,
    this::setTextMode,
    TextMode::fromSpecification,
    ImmutableNode::fromEnum);

  private Consumer<String> updateTextAction;

  public Textbox() {
    // A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle().forStateSetBorderThickness(ControlState.BASE, 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void emptyText() {
    setText(StringCatalog.EMPTY_STRING);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("return x.value;");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return memberText.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TextMode getTextMode() {
    return textMode.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUserInput() {
    return getText();
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
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeUpdateTextAction() {
    updateTextAction = null;
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
  public Textbox setText(final String text) {
    memberText.setValue(text);

    runOptionalUpdateTextActionForText(text);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Textbox setTextMode(final TextMode textMode) {
    this.textMode.setValue(textMode);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unused")
  public Textbox setUpdateTextAction(final Runnable updateTextAction) {
    Validator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    return setUpdateTextAction(t -> updateTextAction.run());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Textbox setUpdateTextAction(final Consumer<String> updateTextAction) {
    Validator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    this.updateTextAction = updateTextAction;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Textbox setUserInput(final String userInput) {
    return setText(userInput);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TextboxStyle createStyle() {
    return new TextboxStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<ITextbox, ITextboxStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<ITextbox> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetControl() {
    emptyText();
    setTextMode(DEFAULT_TEXT_MODE);
    removeUpdateTextAction();

    setCursorIcon(CursorIcon.EDIT);
  }

  private boolean hasUpdateTextAction() {
    return (updateTextAction != null);
  }

  private void runOptionalUpdateTextActionForText(final String text) {
    if (hasUpdateTextAction()) {
      updateTextAction.accept(text);
    }
  }
}
