package ch.nolix.system.webatomiccontrol.button;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabelTool;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webatomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemapi.webatomiccontrol.button.IButtonStyle;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabelTool;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class Button extends Control<IButton, IButtonStyle> implements IButton {
  public static final String DEFAULT_TEXT = StringCatalog.MINUS;

  private static final IValidationLabelTool VALIDATION_LABEL_TOOL = new ValidationLabelTool();

  private static final ButtonHtmlBuilder HTML_BUILDER = new ButtonHtmlBuilder();

  private static final ButtonCssBuilder CSS_BUILDER = new ButtonCssBuilder();

  private final MutableOptionalValue<ButtonRole> memberRole = //
  new MutableOptionalValue<>(
    ButtonAttributeHeaderCatalog.ROLE_HEADER,
    this::setRole,
    ButtonRole::fromSpecification,
    Node::fromEnum);

  private final MutableValue<String> text = //
  MutableValue.forString(ButtonAttributeHeaderCatalog.TEXT_HEADER, DEFAULT_TEXT, this::setText);

  private Consumer<IButton> leftMouseButtonPressAction;

  private Consumer<IButton> leftMouseButtonReleaseAction;

  public Button() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    setMinWidth(200);
    getStoredStyle()
      .forStateSetLeftPadding(ControlState.BASE, 20)
      .forStateSetRightPadding(ControlState.BASE, 20)
      .forStateSetBackgroundColor(ControlState.BASE, X11ColorCatalog.LIGHT_GREY)
      .forStateSetBackgroundColor(ControlState.HOVER, X11ColorCatalog.DARK_GREY)
      .forStateSetBackgroundColor(ControlState.FOCUS, X11ColorCatalog.DARK_GREY);
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public ButtonRole getRole() {
    return memberRole.getValue();
  }

  @Override
  public String getText() {
    return text.getValue();
  }

  @Override
  public String getUserInput() {
    return StringCatalog.EMPTY_STRING;
  }

  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  @Override
  public boolean hasRole() {
    return memberRole.containsAny();
  }

  @Override
  public void pressLeftMouseButton() {
    if (hasLeftMouseButtonPressAction()) {
      VALIDATION_LABEL_TOOL.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonPressAction);
    }
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    if (hasLeftMouseButtonPressAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmousedown"));
    }

    if (hasLeftMouseButtonReleaseAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmouseup"));
    }
  }

  @Override
  public void releaseLeftMouseButton() {
    if (hasLeftMouseButtonReleaseAction()) {
      VALIDATION_LABEL_TOOL.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonReleaseAction);
    }
  }

  @Override
  public void removeLeftMouseButtonPressAction() {
    leftMouseButtonPressAction = null;
  }

  @Override
  public void removeLeftMouseButtonReleaseAction() {
    leftMouseButtonReleaseAction = null;
  }

  @Override
  public void removeRole() {
    memberRole.clear();
  }

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

  @Override
  @SuppressWarnings("unused")
  public IButton setLeftMouseButtonPressAction(final Runnable leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
  }

  @Override
  public IButton setLeftMouseButtonPressAction(final Consumer<IButton> leftMouseButtonPressAction) {
    Validator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    this.leftMouseButtonPressAction = leftMouseButtonPressAction;

    return this;
  }

  @Override
  @SuppressWarnings("unused")
  public IButton setLeftMouseButtonRelaseAction(final Runnable leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
  }

  @Override
  public IButton setLeftMouseButtonRelaseAction(final Consumer<IButton> leftMouseButtonReleaseAction) {
    Validator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;

    return this;
  }

  @Override
  public IButton setRole(final ButtonRole role) {
    memberRole.setValue(role);

    return this;
  }

  @Override
  public IButton setText(final String text) {
    this.text.setValue(text);

    return this;
  }

  @Override
  public IButton setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected IButtonStyle createStyle() {
    return new ButtonStyle();
  }

  @Override
  protected IControlCssBuilder<IButton, IButtonStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<IButton> getHtmlBuilder() {
    return HTML_BUILDER;
  }

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
