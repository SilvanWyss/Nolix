//package declaration
package ch.nolix.system.webgui.main;

import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.system.element.property.ExtensionElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.structure.AbsoluteOrRelativeInt;
import ch.nolix.system.structure.AbsoluteOrRelativeIntValidator;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.presenceapi.Presence;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.structureapi.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public abstract class Control<C extends IControl<C, CS>, CS extends IControlStyle<CS>>
    extends StylableElement<C>
    implements IControl<C, CS> {

  //constant
  public static final Presence DEFAULT_PRESENCE = Presence.VISIBLE;

  //constant
  public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;

  //constant
  private static final String PRESENCE_HEADER = "Presence";

  //constant
  private static final String MIN_WIDTH_HEADER = "MinWidth";

  //constant
  private static final String MIN_HEIGHT_HEADER = "MinHeight";

  //constant
  private static final String MAX_WIDTH_HEADER = "MaxWidth";

  //constant
  private static final String MAX_HEIGHT_HEADER = "MaxHeight";

  //constant
  private static final String CURSOR_ICON_HEADER = PascalCaseCatalogue.CURSOR_ICON;

  //constant
  private static final ControlHelper CONTROL_HELPER = new ControlHelper();

  //constant
  private static final AbsoluteOrRelativeIntValidator ABSOLUTE_OR_RELATIVE_INT_VALIDATOR = //
      new AbsoluteOrRelativeIntValidator();

  //attribute
  //An id works correctly for CSS only when it begins with a letter.
  private final String internalId = "i" + GlobalIdCreator.createIdOf10HexadecimalCharacters();

  //attribute
  private final MutableValue<Presence> presence = new MutableValue<>(
      PRESENCE_HEADER,
      DEFAULT_PRESENCE,
      this::setPresence,
      Presence::fromSpecification,
      Node::fromEnum);

  //attribute
  private final MutableOptionalValue<AbsoluteOrRelativeInt> minWidth = MutableOptionalValue.forElement(
      MIN_WIDTH_HEADER,
      this::setMinWidth,
      AbsoluteOrRelativeInt::fromSpecification);

  //attribute
  private final MutableOptionalValue<AbsoluteOrRelativeInt> minHeight = MutableOptionalValue.forElement(
      MIN_HEIGHT_HEADER,
      this::setMinHeight,
      AbsoluteOrRelativeInt::fromSpecification);

  //attribute
  private final MutableOptionalValue<AbsoluteOrRelativeInt> maxWidth = MutableOptionalValue.forElement(
      MAX_WIDTH_HEADER,
      this::setMaxWidth,
      AbsoluteOrRelativeInt::fromSpecification);

  //attribute
  private final MutableOptionalValue<AbsoluteOrRelativeInt> maxHeight = MutableOptionalValue.forElement(
      MAX_HEIGHT_HEADER,
      this::setMaxHeight,
      AbsoluteOrRelativeInt::fromSpecification);

  //attribute
  private final MutableValue<CursorIcon> cursorIcon = new MutableValue<>(
      CURSOR_ICON_HEADER,
      DEFAULT_CURSOR_ICON,
      this::setCursorIcon,
      CursorIcon::fromSpecification,
      Node::fromEnum);

  //attribute
  private final ExtensionElement<CS> style = new ExtensionElement<>(createStyle());

  //optional attribute
  private ControlParent parent;

  //optional attribute
  private Object linkedObject;

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  @Override
  public final boolean belongsToControl() {
    return (parent != null && parent.isControl());
  }

  //method
  @Override
  public final boolean belongsToGui() {
    return (belongsToLayer() && getStoredParentLayer().belongsToGui());
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  @Override
  public final boolean belongsToLayer() {
    return (parent != null && parent.belongsToLayer());
  }

  //method
  @Override
  public final C editStyle(final Consumer<CS> styleEditor) {

    styleEditor.accept(getStoredStyle());

    return asConcrete();
  }

  //method
  @Override
  public final IContainer<ICssRule> getCssRules() {
    return getCssBuilder().createCssRulesForControl(asConcrete());
  }

  //method
  @Override
  public final CursorIcon getCursorIcon() {
    return cursorIcon.getValue();
  }

  //method
  @Override
  public final IHtmlElement getHtml() {

    final var html = getHtmlBuilder().createHtmlElementForControl(asConcrete());

    return html.withAttribute(CONTROL_HELPER.createIdHtmlAttributeForControl(this));
  }

  //method
  @Override
  public final String getInternalId() {
    return internalId;
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getMaxHeight() {
    return maxHeight.getValue();
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getMaxWidth() {
    return maxWidth.getValue();
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getMinHeight() {
    return minHeight.getValue();
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getMinWidth() {
    return minWidth.getValue();
  }

  //method
  @Override
  public final Presence getPresence() {
    return presence.getValue();
  }

  //method
  @Override
  public final IControl<?, ?> getStoredChildControlOrNullByInternalId(final String internalId) {
    return getStoredChildControls().getStoredFirstOrNull(cs -> cs.hasInternalId(internalId));
  }

  //method
  @Override
  public final IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredChildControls();
  }

  //method
  @Override
  public IContainer<Object> getStoredLinkedObjects() {

    if (!isLinkedToAnObject()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(linkedObject);
  }

  //method
  @Override
  public final IControl<?, ?> getStoredParentControl() {
    return getStoredParent().getStoredControl();
  }

  //method
  @Override
  public final IWebGui<?> getStoredParentGui() {
    return getStoredParentLayer().getStoredParentGui();
  }

  //method
  @Override
  public final ILayer<?> getStoredParentLayer() {
    return getStoredParent().getStoredRootLayer();
  }

  //method
  @Override
  public final CS getStoredStyle() {
    return style.getExtensionElement();
  }

  //method
  @Override
  public final boolean hasInternalId(final String internalId) {
    return getInternalId().equals(internalId);
  }

  //method
  @Override
  public final boolean hasMaxHeight() {
    return maxHeight.containsAny();
  }

  //method
  @Override
  public final boolean hasMaxWidth() {
    return maxWidth.containsAny();
  }

  //method
  @Override
  public final boolean hasMinHeight() {
    return minHeight.containsAny();
  }

  //method
  @Override
  public final boolean hasMinWidth() {
    return minWidth.containsAny();
  }

  //method
  @Override
  public final boolean isCollapsed() {
    return (getPresence() == Presence.COLLAPSED);
  }

  //method
  @Override
  public final boolean isInvisible() {
    return (getPresence() == Presence.INVISIBLE);
  }

  //method
  @Override
  public boolean isLinkedTo(final Object object) {
    return isLinkedToAnObject() && (linkedObject == object);
  }

  //method
  @Override
  public final boolean isLinkedToAnObject() {
    return (linkedObject != null);
  }

  //method
  @Override
  public final boolean isVisible() {
    return (getPresence() == Presence.VISIBLE);
  }

  //method
  @Override
  public final void linkTo(final Object object) {

    GlobalValidator.assertThat(object).thatIsNamed(Object.class).isNotNull();
    assertIsNotLinkedAnObject();

    linkedObject = object;
  }

  //method
  @Override
  public final void removeMaxHeight() {
    maxHeight.clear();
  }

  //method
  @Override
  public final void removeMaxWidth() {
    maxWidth.clear();
  }

  //method
  @Override
  public final void removeMinHeight() {
    minHeight.clear();
  }

  //method
  @Override
  public final void removeMinWidth() {
    minWidth.clear();
  }

  //method
  @Override
  public final C setCollapsed() {

    setPresence(Presence.COLLAPSED);

    return asConcrete();
  }

  //method
  @Override
  public final C setCursorIcon(final CursorIcon cursorIcon) {

    this.cursorIcon.setValue(cursorIcon);

    return asConcrete();
  }

  //method
  @Override
  public final C setInvisible() {

    setPresence(Presence.INVISIBLE);

    return asConcrete();
  }

  //method
  @Override
  public final C setMaxHeight(final int maxHeight) {

    setMaxHeight(AbsoluteOrRelativeInt.withIntValue(maxHeight));

    return asConcrete();
  }

  //method
  @Override
  public final C setMaxHeightInPercentOfViewAreaHeight(final double maxHeightInPercentOfViewAreaHeight) {

    setMaxHeight(AbsoluteOrRelativeInt.withPercentage(maxHeightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  //method
  @Override
  public final C setMaxWidth(final int maxWidth) {

    setMaxWidth(AbsoluteOrRelativeInt.withIntValue(maxWidth));

    return asConcrete();
  }

  //method
  @Override
  public final C setMaxWidthInPercentOfViewAreaWidth(final double maxWidthInPercentOfViewAreaWidth) {

    setMaxWidth(AbsoluteOrRelativeInt.withPercentage(maxWidthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  //method
  @Override
  public final C setMinHeight(final int minHeight) {

    setMinHeight(AbsoluteOrRelativeInt.withIntValue(minHeight));

    return asConcrete();
  }

  //method
  @Override
  public final C setMinHeightInPercentOfViewAreaHeight(final double minHeightInPercentOfViewAreaHeight) {

    setMinHeight(AbsoluteOrRelativeInt.withPercentage(minHeightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  //method
  @Override
  public final C setMinWidth(final int minWidth) {

    setMinWidth(AbsoluteOrRelativeInt.withIntValue(minWidth));

    return asConcrete();
  }

  //method
  @Override
  public final C setMinWidthInPercentOfViewAreaWidth(final double minWidthInPercentOfViewAreaWidth) {

    setMinWidth(AbsoluteOrRelativeInt.withPercentage(minWidthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  //method
  @Override
  public final C setVisible() {

    setPresence(Presence.VISIBLE);

    return asConcrete();
  }

  //method
  @Override
  public final C setVisibility(final boolean visible) {

    voidSetVisibility(visible);

    return asConcrete();
  }

  //method
  @Override
  public final void technicalSetParentControl(final IControl<?, ?> parentControl) {
    setParent(ControlParent.forControl(parentControl));
  }

  //method
  @Override
  public final void technicalSetParentLayer(final ILayer<?> parentLayer) {
    setParent(ControlParent.forLayer(parentLayer));
  }

  //method declaration
  protected abstract CS createStyle();

  //method declaration
  protected abstract IControlCssBuilder<C, CS> getCssBuilder();

  //method declaration
  protected abstract IControlHtmlBuilder<C> getHtmlBuilder();

  //method declaration
  protected abstract void resetControl();

  //method
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

  //method
  @Override
  protected final void resetStyle() {
    getStoredStyle().reset();
  }

  //method
  private void assertBelongsToParent() {
    if (!belongsToParent()) {
      throw ArgumentDoesNotBelongToParentException.forArgument(this);
    }
  }

  //method
  private void assertDoesNotBelongToParent() {
    if (belongsToParent()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, parent.getStoredElement());
    }
  }

  //method
  private void assertIsNotLinkedAnObject() {
    if (isLinkedToAnObject()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is alreay linked to an object");
    }
  }

  //method
  private boolean belongsToParent() {
    return (parent != null);
  }

  //method
  private ControlParent getStoredParent() {

    assertBelongsToParent();

    return parent;
  }

  //method
  private void setMaxHeight(final AbsoluteOrRelativeInt maxHeight) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(maxHeight);

    this.maxHeight.setValue(maxHeight);
  }

  //method
  private void setMaxWidth(final AbsoluteOrRelativeInt maxWidth) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(maxWidth);

    this.maxWidth.setValue(maxWidth);
  }

  //method
  private void setMinHeight(final AbsoluteOrRelativeInt minHeight) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(minHeight);

    this.minHeight.setValue(minHeight);
  }

  //method
  private void setMinWidth(final AbsoluteOrRelativeInt minWidth) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(minWidth);

    this.minWidth.setValue(minWidth);
  }

  //method
  private void setParent(final ControlParent parent) {

    GlobalValidator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
    assertDoesNotBelongToParent();

    this.parent = parent;

    if (parent.isControl()) {
      parent.getStoredControl().getStoredStyle().addChild(getStoredStyle());
    }
  }

  //method
  private void setPresence(final Presence presence) {
    this.presence.setValue(presence);
  }

  //method
  private void voidSetVisibility(final boolean visible) {
    if (!visible) {
      setInvisible();
    } else {
      setVisible();
    }
  }
}
