/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import java.util.Optional;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.datamodel.id.IdCreator;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.style.stylable.AbstractStylableElement;
import ch.nolix.system.webgui.controltool.ControlAnalyser;
import ch.nolix.system.webgui.controltool.ControlTool;
import ch.nolix.system.webgui.mainvalidator.LayerValidator;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.BackgroundType;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.box.ContentAlignment;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.style.stylable.IStylableElement;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class Layer // NOSONAR: A Layer is a principal object thus it has many methods.
extends AbstractStylableElement<ILayer>
implements ILayer {
  public static final double DEFAULT_OPACITY = 1.0;

  public static final IColor DEFAULT_BACKGROUND_COLOR = X11ColorCatalog.WHITE;

  public static final ContentAlignment DEFAULT_CONTENT_POSITION = ContentAlignment.TOP;

  private static final String ROLE_HEADER = PascalCaseVariableNameCatalog.ROLE;

  private static final String OPACITY_HEADER = PascalCaseVariableNameCatalog.OPACITY;

  private static final String BACKGROUND_HEADER = PascalCaseVariableNameCatalog.BACKGROUND;

  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  private static final String ROOT_CONTROL_HEADER = "RootControl";

  private static final LayerValidator LAYER_VALIDATOR = new LayerValidator();

  private static final ControlAnalyser CONTROL_ANALYSER = new ControlAnalyser();

  private static final ControlTool CONTROL_TOOL = new ControlTool();

  // For CSS an id works only when it begins with a letter.
  private final String memberInternalId = "i" + IdCreator.createIdOf10HexadecimalCharacters();

  private final OptionalValue<LayerRole> memberRole = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    ROLE_HEADER,
    this::setRole,
    LayerRole::fromSpecification,
    ImmutableNode::fromEnum);

  private final Value<Double> opacity = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    OPACITY_HEADER,
    DEFAULT_OPACITY,
    this::setOpacity,
    s -> StringTool.toProportion(s.getSingleChildNodeHeader()),
    ImmutableNode::withChildNode);

  private final OptionalValue<IBackground> background = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    BACKGROUND_HEADER,
    this::setBackground,
    Background::fromSpecification,
    IBackground::getSpecification);

  private final Value<ContentAlignment> contentAlignment = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_POSITION,
    this::setContentAlignment,
    ContentAlignment::fromSpecification,
    ImmutableNode::fromEnum);

  private final OptionalValue<Control<?, ?>> memberRootControl = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    ROOT_CONTROL_HEADER,
    this::setRootControl,
    ControlFactory::createControlFromSpecification,
    Control::getSpecification);

  private IWebGui<?> memberParentGui;

  public static Layer fromSpecification(final INode<?> specification) {
    final var layer = new Layer();
    layer.resetFromSpecification(specification);

    return layer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToGui() {
    return (memberParentGui != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsControl(final Control<?, ?> control) {
    return (containsAny() && containsControlWhenContainsAny(control));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    if (containsAny()) {
      clearWhenIsNotEmpty();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IBackground getBackground() {
    return background.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColor getBackgroundColor() {
    return getBackground().getColor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IColorGradient getBackgroundColorGradient() {
    return getBackground().getColorGradient();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IImage getBackgroundImage() {
    return getBackground().getImage();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageApplication getBackgroundImageApplication() {
    return getBackground().getImageApplication();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BackgroundType getBackgroundType() {
    return getBackground().getType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentAlignment getContentAlignment() {
    return contentAlignment.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICssRule getCssRule() {
    return LayerCssBuilder.getCssRuleForLayer(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement getHtml() {
    return LayerHtmlBuilder.getHtmlElementForLayer(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getInternalId() {
    return memberInternalId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getOpacity() {
    return opacity.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Control<?, ?>> getOptionalStoredControlByInternalId(final String internalId) {
    if (isEmpty()) {
      return Optional.empty();
    }

    final var rootControl = getStoredRootControl();

    if (rootControl.hasInternalId(internalId)) {
      return Optional.of(rootControl);
    }

    return rootControl.getOptionalStoredChildControlByInternalId(internalId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredControls() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return CONTROL_TOOL.getListWithControlAndChildControlsRecursively(getStoredRootControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWebGui<?> getStoredParentGui() {
    LAYER_VALIDATOR.assertBelongsToGui(this);

    return memberParentGui;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredRootControl() {
    return memberRootControl.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return CONTROL_TOOL.getListWithControlAndStructureControlsRecursively(getStoredRootControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LayerRole getRole() {
    return memberRole.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IStylableElement<?>> getStoredChildStylableElements() {
    final ILinkedList<Control<?, ?>> childConfigurableElements = LinkedList.createEmpty();

    if (containsAny()) {
      childConfigurableElements.addAtEnd(getStoredRootControl());
    }

    return childConfigurableElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasBackground() {
    return background.containsAny();
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
  public boolean hasRole() {
    return memberRole.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasRole(final String role) {
    return (hasRole() && getRole().toString().equals(role));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void internalSetParentGui(final IWebGui<?> parentGui) {
    Validator.assertThat(parentGui).thatIsNamed("parent GUI").isNotNull();
    LAYER_VALIDATOR.assertDoesNotBelongToGui(this);

    memberParentGui = parentGui;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberRootControl.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeBackground() {
    background.clear();
  }

  public void removeRole() {
    memberRole.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSelfFromGui() {
    if (belongsToGui()) {
      removeSelfFromGuiWhenBelongsToGui();
    }
  }

  public Layer setBackground(final IBackground background) {
    this.background.setValue(background);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setBackgroundColor(final IColor backgroundColor) {
    return setBackground(Background.withColor(backgroundColor));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
    return setBackground(Background.withColorGradient(backgroundColorGradient));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setBackgroundImage(final IImage backgroundImage) {
    return setBackground(Background.withImage(backgroundImage));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
    return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setRootControl(final Control<?, ?> rootControl) {
    final var controlParent = ControlParent.forLayer(this);

    rootControl.internalSetControlParent(controlParent);
    this.memberRootControl.setValue(rootControl);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setContentAlignment(final ContentAlignment contentAlignment) {
    this.contentAlignment.setValue(contentAlignment);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setOpacity(final double opacity) {
    Validator.assertThat(opacity).thatIsNamed("opacity").isBetween(0.0, 1.0);

    this.opacity.setValue(opacity);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layer setRole(final LayerRole role) {
    memberRole.setValue(role);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetStylableElement() {
    removeRole();
    clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetStyle() {
    setOpacity(DEFAULT_OPACITY);
    removeBackground();
    setContentAlignment(DEFAULT_CONTENT_POSITION);
  }

  private void clearWhenIsNotEmpty() {
    memberRootControl.clear();
  }

  private boolean containsControlWhenContainsAny(final Control<?, ?> control) {
    final var localRootControl = getStoredRootControl();

    if (localRootControl == control) {
      return true;
    }

    return CONTROL_ANALYSER.firstControlContainsSecondControl(localRootControl, control);
  }

  private void removeSelfFromGuiWhenBelongsToGui() {
    getStoredParentGui().removeLayer(this);
    memberParentGui = null;
  }
}
