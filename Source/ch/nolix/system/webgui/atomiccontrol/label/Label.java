package ch.nolix.system.webgui.atomiccontrol.label;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabel;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabelStyle;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.LabelRole;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public final class Label extends Control<ILabel, ILabelStyle> implements ILabel {

  public static final String DEFAULT_TEXT = StringCatalog.MINUS;

  private static final String ROLE_HEADER = PascalCaseVariableCatalog.ROLE;

  private static final String TEXT_HEADER = PascalCaseVariableCatalog.TEXT;

  private static final LabelHtmlBuilder HTML_BUILDER = new LabelHtmlBuilder();

  private static final LabelCssBuilder CSS_BUILDER = new LabelCssBuilder();

  private final MutableOptionalValue<LabelRole> role = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    LabelRole::fromSpecification,
    Node::fromEnum);

  private final MutableValue<String> text = MutableValue.forString(TEXT_HEADER, DEFAULT_TEXT, this::setText);

  public Label() {

    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();
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
  public LabelRole getRole() {
    return role.getValue();
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
  public boolean hasRole() {
    return role.containsAny();
  }

  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public void removeRole() {
    role.clear();
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public ILabel setRole(final LabelRole role) {

    this.role.setValue(role);

    return this;
  }

  @Override
  public ILabel setText(final String text) {

    Validator.assertThat(text).thatIsNamed(LowerCaseVariableCatalog.TEXT).isNotNull();

    this.text.setValue(text);

    return this;
  }

  @Override
  public ILabel setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected LabelStyle createStyle() {
    return new LabelStyle();
  }

  @Override
  protected IControlCssBuilder<ILabel, ILabelStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<ILabel> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected void resetControl() {

    removeRole();
    setText(DEFAULT_TEXT);

    setCursorIcon(CursorIcon.EDIT);
  }
}
