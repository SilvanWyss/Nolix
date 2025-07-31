package ch.nolix.system.webgui.atomiccontrol.link;

import java.util.Optional;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.web.url.UrlTool;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.coreapi.web.htmlattribute.LinkTarget;
import ch.nolix.coreapi.web.url.IUrlTool;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILink;
import ch.nolix.systemapi.webgui.atomiccontrol.linkapi.ILinkStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public final class Link extends Control<ILink, ILinkStyle> implements ILink {

  public static final String DEFAULT_DISPLAY_TEXT = StringCatalog.QUESTION_MARK;

  private static final LinkTarget DEFAULT_TARGET = LinkTarget.NEW_TAB;

  private static final String DISPLAY_TEXT_HEADER = "DisplayText";

  private static final String TARGET_HEADER = PascalCaseVariableCatalog.TARGET;

  private static final String URL_HEADER = PascalCaseVariableCatalog.URL;

  private static final LinkHtmlBuilder LINK_HTML_BUILDER = new LinkHtmlBuilder();

  private static final LinkCssBuilder LINK_CSS_BUILDER = new LinkCssBuilder();

  private static final IUrlTool URL_TOOL = new UrlTool();

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
      .setTextColorForState(ControlState.BASE, X11ColorCatalog.BLUE);
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
    return StringCatalog.EMPTY_STRING;
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

    Validator.assertThat(displayText).thatIsNamed("dipslay text").isNotBlank();

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

    Validator.assertThat(url).thatIsNamed(LowerCaseVariableCatalog.URL).isNotBlank();

    this.url.setValue(url);

    return this;
  }

  @Override
  public ILink setUrlAndDisplayTextFromIt(final String url) {

    final var localDisplayText = URL_TOOL.getDisplayTextForUrl(url);

    setUrl(url);
    setDisplayText(localDisplayText);

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
    getStoredStyle().setTextColorForState(ControlState.BASE, X11ColorCatalog.BLUE);
  }
}
