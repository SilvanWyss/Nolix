//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//Java imports
import java.util.Optional;
import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButtonStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelTool;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Button extends Control<IButton, IButtonStyle> implements IButton {

  //constant
  public static final String DEFAULT_TEXT = StringCatalogue.MINUS;

  //constant
  private static final String ROLE_HEADER = PascalCaseVariableCatalogue.ROLE;

  //constant
  private static final String TEXT_HEADER = PascalCaseVariableCatalogue.TEXT;

  //constant
  private static final IValidationLabelTool VALIDATION_LABEL_HELPER = new ValidationLabelTool();

  //constant
  private static final ButtonHtmlBuilder HTML_BUILDER = new ButtonHtmlBuilder();

  //constant
  private static final ButtonCssBuilder CSS_BUILDER = new ButtonCssBuilder();

  //attribute
  private final MutableOptionalValue<ButtonRole> role = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    ButtonRole::fromSpecification,
    Node::fromEnum);

  //attribute
  private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  //optional attribute
  private Consumer<IButton> leftMouseButtonPressAction;

  //optional attribute
  private Consumer<IButton> leftMouseButtonReleaseAction;

  //constructor
  public Button() {

    //Info: Reset is technically optional, but required to achieve a well-defined
    //initial state.
    reset();

    getStoredStyle()
      .setBorderThicknessForState(ControlState.BASE, 1)
      .setLeftPaddingForState(ControlState.BASE, 20)
      .setRightPaddingForState(ControlState.BASE, 20)
      .setBackgroundColorForState(ControlState.HOVER, Color.LIGHT_GREY)
      .setBackgroundColorForState(ControlState.FOCUS, Color.LIGHT_GREY);
  }

  //method
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.empty();
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public ButtonRole getRole() {
    return role.getValue();
  }

  //method
  @Override
  public String getText() {
    return text.getValue();
  }

  //method
  @Override
  public String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  //method
  @Override
  public boolean hasRole() {
    return role.containsAny();
  }

  //method
  @Override
  public void pressLeftMouseButton() {
    if (hasLeftMouseButtonPressAction()) {
      VALIDATION_LABEL_HELPER.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonPressAction);
    }
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {

    if (hasLeftMouseButtonPressAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmousedown"));
    }

    if (hasLeftMouseButtonReleaseAction()) {
      list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onmouseup"));
    }
  }

  //method
  @Override
  public void releaseLeftMouseButton() {
    if (hasLeftMouseButtonReleaseAction()) {
      VALIDATION_LABEL_HELPER.executeActionOfControlAndShowProbableErrorInNearestValidationLabel(
        this,
        leftMouseButtonReleaseAction);
    }
  }

  //method
  @Override
  public void removeLeftMouseButtonPressAction() {
    leftMouseButtonPressAction = null;
  }

  //method
  @Override
  public void removeLeftMouseButtonReleaseAction() {
    leftMouseButtonReleaseAction = null;
  }

  //method
  @Override
  public void removeRole() {
    role.clear();
  }

  //method
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    switch (htmlEvent) {
      case "onmousedown" ->
        pressLeftMouseButton();
      case "onmouseup" ->
        releaseLeftMouseButton();
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument("HTML event", htmlEvent);
    }
  }

  //method
  @Override
  public IButton setLeftMouseButtonPressAction(final Runnable leftMouseButtonPressAction) {

    GlobalValidator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    return setLeftMouseButtonPressAction(b -> leftMouseButtonPressAction.run());
  }

  //method
  @Override
  public IButton setLeftMouseButtonPressAction(final Consumer<IButton> leftMouseButtonPressAction) {

    GlobalValidator
      .assertThat(leftMouseButtonPressAction)
      .thatIsNamed("left mouse button press action")
      .isNotNull();

    this.leftMouseButtonPressAction = leftMouseButtonPressAction;

    return this;
  }

  //method
  @Override
  public IButton setLeftMouseButtonRelaseAction(final Runnable leftMouseButtonReleaseAction) {

    GlobalValidator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    return setLeftMouseButtonRelaseAction(b -> leftMouseButtonReleaseAction.run());
  }

  //method
  @Override
  public IButton setLeftMouseButtonRelaseAction(final Consumer<IButton> leftMouseButtonReleaseAction) {

    GlobalValidator
      .assertThat(leftMouseButtonReleaseAction)
      .thatIsNamed("left mouse button release action")
      .isNotNull();

    this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;

    return this;
  }

  //method
  @Override
  public IButton setRole(final ButtonRole role) {

    this.role.setValue(role);

    return this;
  }

  //method
  @Override
  public IButton setText(final String text) {

    this.text.setValue(text);

    return this;
  }

  //method
  @Override
  public IButton setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  //method
  @Override
  protected IButtonStyle createStyle() {
    return new ButtonStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<IButton, IButtonStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<IButton> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetControl() {

    removeRole();
    setText(DEFAULT_TEXT);
    removeLeftMouseButtonPressAction();
    removeLeftMouseButtonReleaseAction();

    setCursorIcon(CursorIcon.HAND);
  }

  //method
  private boolean hasLeftMouseButtonPressAction() {
    return (leftMouseButtonPressAction != null);
  }

  //method
  private boolean hasLeftMouseButtonReleaseAction() {
    return (leftMouseButtonReleaseAction != null);
  }
}
