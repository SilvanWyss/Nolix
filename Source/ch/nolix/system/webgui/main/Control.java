package ch.nolix.system.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.system.element.property.ExtensionElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeInt;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeIntValidator;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.gui.presence.Presence;
import ch.nolix.systemapi.style.stylable.IStylableElement;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

public abstract class Control //NOSONAR: A Control is a principal object thus it has many methods.
<C extends IControl<C, S>, S extends IControlStyle<S>>
extends AbstractStylableElement<C>
implements IControl<C, S> {
  public static final Presence DEFAULT_PRESENCE = Presence.VISIBLE;

  public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;

  private static final String PRESENCE_HEADER = "Presence";

  private static final String MIN_WIDTH_HEADER = "MinWidth";

  private static final String MIN_HEIGHT_HEADER = "MinHeight";

  private static final String MAX_WIDTH_HEADER = "MaxWidth";

  private static final String MAX_HEIGHT_HEADER = "MaxHeight";

  private static final String CURSOR_ICON_HEADER = PascalCaseVariableCatalog.CURSOR_ICON;

  //An id works correctly for CSS only when it begins with a letter.
  private final String memberInternalId = "i" + IdCreator.createIdOf10HexadecimalCharacters();

  private final MutableValue<Presence> presence = new MutableValue<>(
    PRESENCE_HEADER,
    DEFAULT_PRESENCE,
    this::setPresence,
    Presence::fromSpecification,
    Node::fromEnum);

  private final MutableOptionalValue<AbsoluteOrRelativeInt> minWidth = MutableOptionalValue.forElement(
    MIN_WIDTH_HEADER,
    this::setMinWidth,
    AbsoluteOrRelativeInt::fromSpecification);

  private final MutableOptionalValue<AbsoluteOrRelativeInt> minHeight = MutableOptionalValue.forElement(
    MIN_HEIGHT_HEADER,
    this::setMinHeight,
    AbsoluteOrRelativeInt::fromSpecification);

  private final MutableOptionalValue<AbsoluteOrRelativeInt> maxWidth = MutableOptionalValue.forElement(
    MAX_WIDTH_HEADER,
    this::setMaxWidth,
    AbsoluteOrRelativeInt::fromSpecification);

  private final MutableOptionalValue<AbsoluteOrRelativeInt> maxHeight = MutableOptionalValue.forElement(
    MAX_HEIGHT_HEADER,
    this::setMaxHeight,
    AbsoluteOrRelativeInt::fromSpecification);

  private final MutableValue<CursorIcon> cursorIcon = new MutableValue<>(
    CURSOR_ICON_HEADER,
    DEFAULT_CURSOR_ICON,
    this::setCursorIcon,
    CursorIcon::fromSpecification,
    Node::fromEnum);

  private final ExtensionElement<S> style = new ExtensionElement<>(createStyle());

  private ControlParent parent;

  private Object linkedObject;

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public final boolean belongsToControl() {
    return (parent != null && parent.isControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean belongsToGui() {
    return (belongsToLayer() && getStoredParentLayer().belongsToGui());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public final boolean belongsToLayer() {
    return (parent != null && parent.belongsToLayer());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C editStyle(final Consumer<S> styleEditor) {
    styleEditor.accept(getStoredStyle());

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<ICssRule> getCssRules() {
    return getCssBuilder().createCssRulesForControl(asConcrete());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final CursorIcon getCursorIcon() {
    return cursorIcon.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IHtmlElement getHtml() {
    final var html = getHtmlBuilder().createHtmlElementForControl(asConcrete());

    return html.withAttribute(ControlHelper.createIdHtmlAttributeForControl(this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getInternalId() {
    return memberInternalId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMaxHeight() {
    return maxHeight.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMaxWidth() {
    return maxWidth.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMinHeight() {
    return minHeight.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMinWidth() {
    return minWidth.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Presence getPresence() {
    return presence.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Optional<IControl<?, ?>> getOptionalStoredChildControlByInternalId(final String internalId) {
    return getStoredChildControls().getOptionalStoredFirst(cs -> cs.hasInternalId(internalId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredChildControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<Object> getStoredLinkedObjects() {
    if (!isLinkedToAnObject()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(linkedObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IControl<?, ?> getStoredParentControl() {
    return getStoredParent().getStoredControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IWebGui<?> getStoredParentGui() {
    return getStoredParentLayer().getStoredParentGui();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ILayer<?> getStoredParentLayer() {
    return getStoredParent().getStoredRootLayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredStyle() {
    return style.getExtensionElement();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasInternalId(final String internalId) {
    return getInternalId().equals(internalId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasMaxHeight() {
    return maxHeight.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasMaxWidth() {
    return maxWidth.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasMinHeight() {
    return minHeight.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasMinWidth() {
    return minWidth.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetParentControl(final IControl<?, ?> parentControl) {
    setParent(ControlParent.forControl(parentControl));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetParentLayer(final ILayer<?> parentLayer) {
    setParent(ControlParent.forLayer(parentLayer));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isCollapsed() {
    return (getPresence() == Presence.COLLAPSED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isInvisible() {
    return (getPresence() == Presence.INVISIBLE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLinkedTo(final Object object) {
    return isLinkedToAnObject() && (linkedObject == object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isLinkedToAnObject() {
    return (linkedObject != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isVisible() {
    return (getPresence() == Presence.VISIBLE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void linkTo(final Object object) {
    Validator.assertThat(object).thatIsNamed(Object.class).isNotNull();
    assertIsNotLinkedAnObject();

    linkedObject = object;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeMaxHeight() {
    maxHeight.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeMaxWidth() {
    maxWidth.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeMinHeight() {
    minHeight.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeMinWidth() {
    minWidth.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setCollapsed() {
    setPresence(Presence.COLLAPSED);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setCursorIcon(final CursorIcon cursorIcon) {
    this.cursorIcon.setValue(cursorIcon);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setInvisible() {
    setPresence(Presence.INVISIBLE);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMaxHeight(final int maxHeight) {
    setMaxHeight(AbsoluteOrRelativeInt.withIntValue(maxHeight));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMaxHeightInPercentOfViewAreaHeight(final double maxHeightInPercentOfViewAreaHeight) {
    setMaxHeight(AbsoluteOrRelativeInt.withPercentage(maxHeightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMaxWidth(final int maxWidth) {
    setMaxWidth(AbsoluteOrRelativeInt.withIntValue(maxWidth));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMaxWidthInPercentOfViewAreaWidth(final double maxWidthInPercentOfViewAreaWidth) {
    setMaxWidth(AbsoluteOrRelativeInt.withPercentage(maxWidthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMinHeight(final int minHeight) {
    setMinHeight(AbsoluteOrRelativeInt.withIntValue(minHeight));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMinHeightInPercentOfViewAreaHeight(final double minHeightInPercentOfViewAreaHeight) {
    setMinHeight(AbsoluteOrRelativeInt.withPercentage(minHeightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMinWidth(final int minWidth) {
    setMinWidth(AbsoluteOrRelativeInt.withIntValue(minWidth));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setMinWidthInPercentOfViewAreaWidth(final double minWidthInPercentOfViewAreaWidth) {
    setMinWidth(AbsoluteOrRelativeInt.withPercentage(minWidthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setVisible() {
    setPresence(Presence.VISIBLE);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C setVisibility(final boolean visible) {
    voidSetVisibility(visible);

    return asConcrete();
  }

  protected abstract S createStyle();

  protected abstract IControlCssBuilder<C, S> getCssBuilder();

  protected abstract IControlHtmlBuilder<C> getHtmlBuilder();

  protected abstract void resetControl();

  @Override
  protected final void resetStylableElement() {
    setVisible();
    removeMinWidth();
    removeMinHeight();
    removeMaxWidth();
    removeMaxHeight();
    setCursorIcon(DEFAULT_CURSOR_ICON);

    resetControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetStyle() {
    getStoredStyle().reset();
  }

  private void assertBelongsToParent() {
    if (!belongsToParent()) {
      throw ArgumentDoesNotBelongToParentException.forArgument(this);
    }
  }

  private void assertDoesNotBelongToParent() {
    if (belongsToParent()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, parent.getStoredElement());
    }
  }

  private void assertIsNotLinkedAnObject() {
    if (isLinkedToAnObject()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is alreay linked to an object");
    }
  }

  private boolean belongsToParent() {
    return (parent != null);
  }

  private ControlParent getStoredParent() {
    assertBelongsToParent();

    return parent;
  }

  private void setMaxHeight(final AbsoluteOrRelativeInt maxHeight) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(maxHeight);

    this.maxHeight.setValue(maxHeight);
  }

  private void setMaxWidth(final AbsoluteOrRelativeInt maxWidth) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(maxWidth);

    this.maxWidth.setValue(maxWidth);
  }

  private void setMinHeight(final AbsoluteOrRelativeInt minHeight) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(minHeight);

    this.minHeight.setValue(minHeight);
  }

  private void setMinWidth(final AbsoluteOrRelativeInt minWidth) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(minWidth);

    this.minWidth.setValue(minWidth);
  }

  private void setParent(final ControlParent parent) {
    Validator.assertThat(parent).thatIsNamed(LowerCaseVariableCatalog.PARENT).isNotNull();
    assertDoesNotBelongToParent();

    this.parent = parent;

    if (parent.isControl()) {
      parent.getStoredControl().getStoredStyle().addChild(getStoredStyle());
    }
  }

  private void setPresence(final Presence presence) {
    this.presence.setValue(presence);
  }

  private void voidSetVisibility(final boolean visible) {
    if (!visible) {
      setInvisible();
    } else {
      setVisible();
    }
  }
}
