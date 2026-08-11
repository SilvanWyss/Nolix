/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.button;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.system.control.validationlabel.ValidationLabelTool;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.html.HtmlElementEvent;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.control.button.IButtonStyle;
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class Button extends AbstractControl<IButton, IButtonStyle> implements IButton {
  public static final String DEFAULT_TEXT = StringCatalog.MINUS;

  private static final ValidationLabelTool VALIDATION_LABEL_TOOL = new ValidationLabelTool();

  private static final ButtonHtmlBuilder HTML_BUILDER = new ButtonHtmlBuilder();

  private static final ButtonCssBuilder CSS_BUILDER = new ButtonCssBuilder();

  private final OptionalValueProperty<ButtonRole> memberRole = //
  OptionalValueProperty.withNameAndSetterAndValueMapperAndSpecificationMapper(
    ButtonAttributeHeaderCatalog.ROLE_HEADER,
    this::setRole,
    ButtonRole::fromSpecification,
    ImmutableNode::fromEnum);

  private final ValueProperty<String> text = //
  ValueProperty.forStringWithNameAndDefaultValueAndSetter(
    ButtonAttributeHeaderCatalog.TEXT_HEADER,
    DEFAULT_TEXT,
    this::setText);

  private Consumer<IButton> leftMouseButtonPressAction;

  private Consumer<IButton> leftMouseButtonReleaseAction;

  public Button() {
    // A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    setMinWidth(200);
    getStoredStyle()
      .forStateSetLeftPadding(ControlState.BASE, 20)
      .forStateSetRightPadding(ControlState.BASE, 20)
      .forStateSetBackgroundColor(ControlState.BASE, X11ColorCatalog.LIGHT_GREY)
      .forStateSetBackgroundColor(ControlState.HOVER, X11ColorCatalog.DARK_GREY)
      .forStateSetBackgroundColor(ControlState.FOCUS, X11ColorCatalog.DARK_GREY);
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
  public ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ButtonRole getRole() {
    return memberRole.getStoredValue();
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
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
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
  public void pressLeftMouseButton() {
    if (hasLeftMouseButtonPressAction()) {
      VALIDATION_LABEL_TOOL.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonPressAction);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    if (hasLeftMouseButtonPressAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmousedown"));
    }

    if (hasLeftMouseButtonReleaseAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmouseup"));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void releaseLeftMouseButton() {
    if (hasLeftMouseButtonReleaseAction()) {
      VALIDATION_LABEL_TOOL.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonReleaseAction);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeLeftMouseButtonPressAction() {
    leftMouseButtonPressAction = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeLeftMouseButtonReleaseAction() {
    leftMouseButtonReleaseAction = null;
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
    switch (htmlEvent) {
      case "onmousedown" ->
        pressLeftMouseButton();
      case "onmouseup" ->
        releaseLeftMouseButton();
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(htmlEvent, "HTML event");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unused")
  public IButton setLeftMouseButtonPressAction(final Runnable leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButton setLeftMouseButtonPressAction(final Consumer<IButton> leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    this.leftMouseButtonPressAction = leftMouseButtonPressAction;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unused")
  public IButton setLeftMouseButtonRelaseAction(final Runnable leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButton setLeftMouseButtonRelaseAction(final Consumer<IButton> leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButton setRole(final ButtonRole role) {
    memberRole.setValue(role);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButton setText(final String text) {
    this.text.setValue(text);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButton setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IButtonStyle createStyle() {
    return new ButtonStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IButton, IButtonStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IButton> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetControl() {
    removeRole();
    setText(DEFAULT_TEXT);
    removeLeftMouseButtonPressAction();
    removeLeftMouseButtonReleaseAction();

    setCursorIcon(CursorIcon.HAND);
  }

  private boolean hasLeftMouseButtonPressAction() {
    return (leftMouseButtonPressAction != null);
  }

  private boolean hasLeftMouseButtonReleaseAction() {
    return (leftMouseButtonReleaseAction != null);
  }
}
