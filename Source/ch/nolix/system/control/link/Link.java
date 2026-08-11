/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.link;

import java.util.Optional;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.base.web.url.UrlTool;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.systemapi.control.link.ILink;
import ch.nolix.systemapi.control.link.ILinkStyle;
import ch.nolix.systemapi.control.link.LinkTarget;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class Link extends AbstractControl<ILink, ILinkStyle> implements ILink {
  public static final String DEFAULT_DISPLAY_TEXT = StringCatalog.QUESTION_MARK;

  private static final LinkTarget DEFAULT_TARGET = LinkTarget.NEW_TAB;

  private static final String DISPLAY_TEXT_HEADER = "DisplayText";

  private static final String TARGET_HEADER = PascalCaseVariableNameCatalog.TARGET;

  private static final String URL_HEADER = PascalCaseVariableNameCatalog.URL;

  private static final LinkHtmlBuilder LINK_HTML_BUILDER = new LinkHtmlBuilder();

  private static final LinkCssBuilder LINK_CSS_BUILDER = new LinkCssBuilder();

  private static final UrlTool URL_TOOL = new UrlTool();

  private final ValueProperty<String> displayText = //
  ValueProperty.forStringWithNameAndDefaultValueAndSetter(
    DISPLAY_TEXT_HEADER,
    DEFAULT_DISPLAY_TEXT,
    this::setDisplayText);

  private final ValueProperty<LinkTarget> target = //
  ValueProperty.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    TARGET_HEADER,
    DEFAULT_TARGET,
    this::setTarget,
    s -> LinkTarget.valueOf(s.getSingleChildNodeHeader()),
    ImmutableNode::fromEnum);

  private final OptionalValueProperty<String> url = //
  OptionalValueProperty.forStringWithNameAndSetter(URL_HEADER, this::setUrl);

  public Link() {
    // Info: Reset is technically optional, but required to achieve a well-defined initial state.
    reset();

    getStoredStyle()
      .forStateSetTextLineDecoration(ControlState.HOVER, LineDecoration.UNDERLINE)
      .forStateSetTextColor(ControlState.BASE, X11ColorCatalog.BLUE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDisplayText() {
    return displayText.getStoredValue();
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
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkTarget getTarget() {
    return target.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUrl() {
    return url.getStoredValue();
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
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasUrl() {
    return url.containsAny();
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
  public void removeUrl() {
    url.clear();
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
  public ILink setDisplayText(final String displayText) {
    Validator.assertThat(displayText).thatIsNamed("dipslay text").isNotBlank();

    this.displayText.setValue(displayText);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILink setTarget(final LinkTarget target) {
    this.target.setValue(target);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILink setUrl(final String url) {
    Validator.assertThat(url).thatIsNamed(LowerCaseVariableNameCatalog.URL).isNotBlank();

    this.url.setValue(url);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILink setUrlAndDisplayTextFromIt(final String url) {
    final var localDisplayText = URL_TOOL.getDisplayTextForUrl(url);

    setUrl(url);
    setDisplayText(localDisplayText);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILink setUserInput(final String userInput) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUserInput");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILinkStyle createStyle() {
    return new LinkStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<ILink, ILinkStyle> getCssBuilder() {
    return LINK_CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<ILink> getHtmlBuilder() {
    return LINK_HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetControl() {
    setDisplayText(DEFAULT_DISPLAY_TEXT);
    removeUrl();

    setCursorIcon(CursorIcon.HAND);
    getStoredStyle().forStateSetTextColor(ControlState.BASE, X11ColorCatalog.BLUE);
  }
}
