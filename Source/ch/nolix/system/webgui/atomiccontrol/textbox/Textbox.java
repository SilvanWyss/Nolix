package ch.nolix.system.webgui.atomiccontrol.textbox;

import java.util.Optional;
import java.util.function.Consumer;

//own import
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextboxStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.TextMode;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public final class Textbox extends Control<ITextbox, ITextboxStyle> implements ITextbox {

  public static final String DEFAULT_TEXT = StringCatalog.EMPTY_STRING;

  public static final TextMode DEFAULT_TEXT_MODE = TextMode.NORMAL;

  private static final String TEXT_HEADER = PascalCaseVariableCatalog.TEXT;

  private static final String TEXT_MODE_HEADER = "TextMode";

  private static final TextboxHtmlBuilder HTML_BUILDER = new TextboxHtmlBuilder();

  private static final TextboxCssBuilder CSS_BUILDER = new TextboxCssBuilder();

  private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  private MutableValue<TextMode> textMode = new MutableValue<>(
    TEXT_MODE_HEADER,
    DEFAULT_TEXT_MODE,
    this::setTextMode,
    TextMode::fromSpecification,
    Node::fromEnum);

  private Consumer<String> updateTextAction;

  public Textbox() {

    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle()
      .setBorderThicknessForState(ControlState.BASE, 1)
      .setBackgroundColorForState(ControlState.BASE, X11ColorCatalog.AQUAMARINE)
      .setBackgroundColorForState(ControlState.HOVER, X11ColorCatalog.MEDIUM_AQUA_MARINE)
      .setBackgroundColorForState(ControlState.FOCUS, X11ColorCatalog.MEDIUM_AQUA_MARINE);
  }

  @Override
  public void emptyText() {
    setText(StringCatalog.EMPTY_STRING);
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("return x.value;");
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public String getText() {
    return text.getValue();
  }

  @Override
  public TextMode getTextMode() {
    return textMode.getValue();
  }

  @Override
  public String getUserInput() {
    return getText();
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public void removeUpdateTextAction() {
    updateTextAction = null;
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public Textbox setText(final String text) {

    this.text.setValue(text);

    runOptionalUpdateTextActionForText(text);

    return this;
  }

  @Override
  public Textbox setTextMode(final TextMode textMode) {

    this.textMode.setValue(textMode);

    return this;
  }

  @Override
  public Textbox setUpdateTextAction(final Runnable updateTextAction) {

    GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    return setUpdateTextAction(t -> updateTextAction.run());
  }

  @Override
  public Textbox setUpdateTextAction(final Consumer<String> updateTextAction) {

    GlobalValidator.assertThat(updateTextAction).thatIsNamed("update text action").isNotNull();

    this.updateTextAction = updateTextAction;

    return this;
  }

  @Override
  public Textbox setUserInput(final String userInput) {
    return setText(userInput);
  }

  @Override
  protected TextboxStyle createStyle() {
    return new TextboxStyle();
  }

  @Override
  protected IControlCssBuilder<ITextbox, ITextboxStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<ITextbox> getHtmlBuilder() {
    return HTML_BUILDER;
  }

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
