package ch.nolix.system.webgui.atomiccontrol;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.fontapi.LineDecoration;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILinkStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public final class Link extends Control<ILink, ILinkStyle> implements ILink {

  public static final String DEFAULT_DISPLAY_TEXT = StringCatalogue.QUESTION_MARK;

  private static final LinkTarget DEFAULT_TARGET = LinkTarget.NEW_TAB;

  private static final String DISPLAY_TEXT_HEADER = "DisplayText";

  private static final String TARGET_HEADER = PascalCaseVariableCatalogue.TARGET;

  private static final String URL_HEADER = PascalCaseVariableCatalogue.URL;

  private static final LinkHtmlBuilder LINK_HTML_BUILDER = new LinkHtmlBuilder();

  private static final LinkCssBuilder LINK_CSS_BUILDER = new LinkCssBuilder();

  private final MutableValue<String> displayText = MutableValue.forString(DISPLAY_TEXT_HEADER, DEFAULT_DISPLAY_TEXT,
    this::setDisplayText);

  private final MutableValue<LinkTarget> target = new MutableValue<>(
    TARGET_HEADER,
    DEFAULT_TARGET,
    this::setTarget,
    s -> LinkTarget.valueOf(s.getSingleChildNodeHeader()),
    Node::fromEnum);

  private final MutableOptionalValue<String> url = MutableOptionalValue.forString(URL_HEADER, this::setUrl);

  public Link() {

    //Info: Reset is technically optional, but required to achieve a well-defined initial state.
    reset();

    getStoredStyle()
      .setTextLineDecorationForState(ControlState.HOVER, LineDecoration.UNDERLINE)
      .setTextColorForState(ControlState.BASE, Color.BLUE);
  }

  @Override
  public String getDisplayText() {
    return displayText.getValue();
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
  public LinkTarget getTarget() {
    return target.getValue();
  }

  @Override
  public String getUrl() {
    return url.getValue();
  }

  @Override
  public String getUserInput() {
    return StringCatalogue.EMPTY_STRING;
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public boolean hasUrl() {
    return url.containsAny();
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  public void removeUrl() {
    url.clear();
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "runHtmlEvent");
  }

  @Override
  public ILink setDisplayText(final String displayText) {

    GlobalValidator.assertThat(displayText).thatIsNamed("dipslay text").isNotBlank();

    this.displayText.setValue(displayText);

    return this;
  }

  @Override
  public ILink setTarget(final LinkTarget target) {

    this.target.setValue(target);

    return this;
  }

  @Override
  public ILink setUrl(final String url) {

    GlobalValidator.assertThat(url).thatIsNamed(LowerCaseVariableCatalogue.URL).isNotBlank();

    this.url.setValue(url);

    return this;
  }

  @Override
  public ILink setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  @Override
  protected ILinkStyle createStyle() {
    return new LinkStyle();
  }

  @Override
  protected IControlCssBuilder<ILink, ILinkStyle> getCssBuilder() {
    return LINK_CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<ILink> getHtmlBuilder() {
    return LINK_HTML_BUILDER;
  }

  @Override
  protected void resetControl() {

    setDisplayText(DEFAULT_DISPLAY_TEXT);
    removeUrl();

    setCursorIcon(CursorIcon.HAND);
    getStoredStyle().setTextColorForState(ControlState.BASE, Color.BLUE);
  }
}
