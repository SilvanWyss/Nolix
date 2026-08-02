/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.base.datamodel.id.IdCreator;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeInt;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeIntValidator;
import ch.nolix.system.property.extension.ExtensionProperty;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.gui.presence.Presence;
import ch.nolix.systemapi.style.stylable.IStylableElement;
import ch.nolix.systemapi.webgui.controlstructure.IControlParent;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractControl}.
 * @param <S> the type of the {@link IControlStyle} of a
 *            {@link AbstractControl}.
 */
public abstract class AbstractControl // NOSONAR: A AbstractControl is a principal object thus it has many methods.
<C extends Control<C, S>, S extends IControlStyle<S>>
extends AbstractStylableElement<C>
implements Control<C, S> {
  public static final Presence DEFAULT_PRESENCE = Presence.VISIBLE;

  public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;

  private static final String PRESENCE_HEADER = "Presence";

  private static final String MIN_WIDTH_HEADER = "MinWidth";

  private static final String MIN_HEIGHT_HEADER = "MinHeight";

  private static final String MAX_WIDTH_HEADER = "MaxWidth";

  private static final String MAX_HEIGHT_HEADER = "MaxHeight";

  private static final String CURSOR_ICON_HEADER = PascalCaseVariableNameCatalog.CURSOR_ICON;

  // An id works correctly for CSS only when it begins with a letter.
  private final String memberInternalId = "i" + IdCreator.createIdOf10HexadecimalCharacters();

  private final Value<Presence> presence = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    PRESENCE_HEADER,
    DEFAULT_PRESENCE,
    this::setPresence,
    Presence::fromSpecification,
    ImmutableNode::fromEnum);

  private final OptionalValue<AbsoluteOrRelativeInt> minWidth = //
  OptionalValue.forElementWithNameAndSetterAndValueMapper(
    MIN_WIDTH_HEADER,
    this::setMinWidth,
    AbsoluteOrRelativeInt::fromSpecification);

  private final OptionalValue<AbsoluteOrRelativeInt> minHeight = //
  OptionalValue.forElementWithNameAndSetterAndValueMapper(
    MIN_HEIGHT_HEADER,
    this::setMinHeight,
    AbsoluteOrRelativeInt::fromSpecification);

  private final OptionalValue<AbsoluteOrRelativeInt> maxWidth = //
  OptionalValue.forElementWithNameAndSetterAndValueMapper(
    MAX_WIDTH_HEADER,
    this::setMaxWidth,
    AbsoluteOrRelativeInt::fromSpecification);

  private final OptionalValue<AbsoluteOrRelativeInt> maxHeight = //
  OptionalValue.forElementWithNameAndSetterAndValueMapper(
    MAX_HEIGHT_HEADER,
    this::setMaxHeight,
    AbsoluteOrRelativeInt::fromSpecification);

  private final Value<CursorIcon> cursorIcon = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    CURSOR_ICON_HEADER,
    DEFAULT_CURSOR_ICON,
    this::setCursorIcon,
    CursorIcon::fromSpecification,
    ImmutableNode::fromEnum);

  private final ExtensionProperty<S> style = ExtensionProperty.withExtension(createStyle());

  private IControlParent parent;

  // For a better performance, this implementation does not use all available comfort methods.
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

  // For a better performance, this implementation does not use all available comfort methods.
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
  public final ExtendedIterable<ICssRule> getCssRules() {
    return getCssBuilder().createCssRulesForControl(asConcrete());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final CursorIcon getCursorIcon() {
    return cursorIcon.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IHtmlElement getHtml() {
    final var html = getHtmlBuilder().createHtmlElementForControl(asConcrete());

    return html.withAdditionalAttributes(ControlHelper.createIdHtmlAttributeForControl(this));
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
    return maxHeight.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMaxWidth() {
    return maxWidth.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMinHeight() {
    return minHeight.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IAbsoluteOrRelativeInt getMinWidth() {
    return minWidth.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Presence getPresence() {
    return presence.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Optional<Control<?, ?>> getOptionalStoredChildControlByInternalId(final String internalId) {
    return getStoredChildControls().getOptionalStoredFirst(cs -> cs.hasInternalId(internalId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredChildControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Control<?, ?> getStoredParentControl() {
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
  public final ILayer getStoredParentLayer() {
    return getStoredParent().getStoredParentLayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getStoredStyle() {
    return style.getStoredExtension();
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
  public final void internalRemoveControlParent() {
    if (belongsToControl()) {
      getStoredParentControl().getStoredStyle().removeChild(getStoredStyle());
    }

    parent = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalSetControlParent(final IControlParent controlParent) {
    Validator.assertThat(controlParent).thatIsNamed(IControlParent.class).isNotNull();
    assertDoesNotBelongToParent();

    parent = controlParent;

    if (parent.isControl()) {
      parent.getStoredControl().getStoredStyle().addChild(getStoredStyle());
    }
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
  public final boolean isVisible() {
    return (getPresence() == Presence.VISIBLE);
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
  public final void removeParentLayerFromGui() {
    getStoredParentLayer().removeSelfFromGui();
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
      if (parent.isControl()) {
        throw ArgumentBelongsToParentException.forArgumentAndParent(this, parent.getStoredControl());
      }
      if (parent.isLayer()) {
        throw ArgumentBelongsToParentException.forArgumentAndParent(this, parent.getStoredLayer());
      }
      throw InvalidArgumentException.forArgumentAndArgumentName(parent, LowerCaseVariableNameCatalog.PARENT);
    }
  }

  private boolean belongsToParent() {
    return (parent != null);
  }

  private IControlParent getStoredParent() {
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
