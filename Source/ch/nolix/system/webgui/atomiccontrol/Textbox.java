//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//Java imports
import java.util.Optional;
import java.util.function.Consumer;

//own import
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.TextMode;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Textbox extends Control<ITextbox, ITextboxStyle> implements ITextbox {

  //constant
  public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;

  //constant
  public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;

  //constant
  private static final String TEXT_HEADER = PascalCaseVariableCatalogue.TEXT;

  //constant
  private static final String TEXT_MODE_HEADER = "TextMode";

  //constant
  private static final TextboxHtmlBuilder HTML_BUILDER = new TextboxHtmlBuilder();

  //constant
  private static final TextboxCssBuilder CSS_BUILDER = new TextboxCssBuilder();

  //attribute
  private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  //attribute
  private MutableValue<TextMode> textMode = new MutableValue<>(
    TEXT_MODE_HEADER,
    DEFAULT_TEXT_MODE,
    this::setTextMode,
    TextMode::fromSpecification,
    Node::fromEnum);

  //optional attribute
  private Consumer<String> updateTextAction;

  //constructor
  public Textbox() {

    //Info: Reset is technically optional, but required to achieve a well-defined
    //initial state.
    reset();

    getStoredStyle()
      .setBorderThicknessForState(ControlState.BASE, 1)
      .setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
      .setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
      .setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
  }

  //method
  @Override
  public void emptyText() {
    setText(StringCatalogue.EMPTY_STRING);
  }

  //method
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("return x.value;");
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public String getText() {
    return text.getValue();
  }

  //method
  @Override
  public TextMode getTextMode() {
    return textMode.getValue();
  }

  //method
  @Override
  public String getUserInput() {
    return getText();
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  public void removeUpdateTextAction() {
    updateTextAction = null;
  }

  //method
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  //method
  @Override
  public Textbox setText(final String text) {

    this.text.setValue(text);

    runOptionalUpdateTextActionForText(text);

    return this;
  }

  //method
  @Override
  public Textbox setTextMode(final TextMode textMode) {

    this.textMode.setValue(textMode);

    return this;
  }

  //method
  @Override
  public Textbox setUpdateTextAction(final Runnable updateTextAction) {

    GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    return setUpdateTextAction(t -> updateTextAction.run());
  }

  //method
  @Override
  public Textbox setUpdateTextAction(final Consumer<String> updateTextAction) {

    GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    this.updateTextAction = updateTextAction;

    return this;
  }

  //method
  @Override
  public Textbox setUserInput(final String userInput) {
    return setText(userInput);
  }

  //method
  @Override
  protected TextboxStyle createStyle() {
    return new TextboxStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<ITextbox, ITextboxStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<ITextbox> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetControl() {

    emptyText();
    setTextMode(DEFAULT_TEXT_MODE);
    removeUpdateTextAction();

    setCursorIcon(CursorIcon.EDIT);
  }

  //method
  private boolean hasUpdateTextAction() {
    return (updateTextAction != null);
  }

  //method
  private void runOptionalUpdateTextActionForText(final String text) {
    if (hasUpdateTextAction()) {
      updateTextAction.accept(text);
    }
  }
}
