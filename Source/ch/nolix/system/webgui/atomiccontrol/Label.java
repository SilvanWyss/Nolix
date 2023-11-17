//package declaration
package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Label extends Control<ILabel, ILabelStyle> implements ILabel {

  //constant
  public static final String DEFAULT_TEXT = StringCatalogue.MINUS;

  //constant
  private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;

  //constant
  private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;

  //constant
  private static final LabelHtmlBuilder HTML_BUILDER = new LabelHtmlBuilder();

  //constant
  private static final LabelCssBuilder CSS_BUILDER = new LabelCssBuilder();

  //attribute
  private final MutableOptionalValue<LabelRole> role = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    LabelRole::fromSpecification,
    Node::fromEnum);

  //attribute
  private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  //constructor
  public Label() {

    //Info: Reset is technically optional, but required to achieve a well-defined
    //initial state.
    reset();
  }

  //method
  @Override
  public ISingleContainer<String> getOptionalJavaScriptUserInputFunction() {
    return new SingleContainer<>();
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public LabelRole getRole() {
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
  public boolean hasRole() {
    return role.containsAny();
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  public void removeRole() {
    role.clear();
  }

  //method
  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  //method
  @Override
  public ILabel setRole(final LabelRole role) {

    this.role.setValue(role);

    return this;
  }

  //method
  @Override
  public ILabel setText(final String text) {

    GlobalValidator.assertThat(text).thatIsNamed(LowerCaseCatalogue.TEXT).isNotNull();

    this.text.setValue(text);

    return this;
  }

  //method
  @Override
  public ILabel setUserInput(final String userInput) {

    GlobalValidator.assertThat(userInput).thatIsNamed("user input").isBlank();

    return this;
  }

  //method
  @Override
  protected LabelStyle createStyle() {
    return new LabelStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<ILabel, ILabelStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<ILabel> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetControl() {

    removeRole();
    setText(DEFAULT_TEXT);

    setCursorIcon(CursorIcon.EDIT);
  }
}
