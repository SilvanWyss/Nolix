package ch.nolix.system.webgui.main;

import java.util.Optional;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
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
import ch.nolix.systemapi.webgui.controltool.IControlTool;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class Layer //NOSONAR: A Layer is a principal object thus it has many methods.
extends AbstractStylableElement<Layer>
implements ILayer<Layer> {
  public static final double DEFAULT_OPACITY = 1.0;

  public static final IColor DEFAULT_BACKGROUND_COLOR = X11ColorCatalog.WHITE;

  public static final ContentAlignment DEFAULT_CONTENT_POSITION = ContentAlignment.TOP;

  private static final String ROLE_HEADER = PascalCaseVariableCatalog.ROLE;

  private static final String OPACITY_HEADER = PascalCaseVariableCatalog.OPACITY;

  private static final String BACKGROUND_HEADER = PascalCaseVariableCatalog.BACKGROUND;

  private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";

  private static final String ROOT_CONTROL_HEADER = "RootControl";

  private static final LayerValidator LAYER_VALIDATOR = new LayerValidator();

  private static final ControlAnalyser CONTROL_ANALYSER = new ControlAnalyser();

  private static final IControlTool CONTROL_TOOL = new ControlTool();

  //For CSS an id works only when it begins with a letter.
  private final String memberInternalId = "i" + IdCreator.createIdOf10HexadecimalCharacters();

  private final MutableOptionalValue<LayerRole> memberRole = new MutableOptionalValue<>(
    ROLE_HEADER,
    this::setRole,
    LayerRole::fromSpecification,
    Node::fromEnum);

  private final MutableValue<Double> opacity = new MutableValue<>(
    OPACITY_HEADER,
    DEFAULT_OPACITY,
    this::setOpacity,
    s -> StringTool.toProportion(s.getSingleChildNodeHeader()),
    Node::withChildNode);

  private final MutableOptionalValue<IBackground> background = new MutableOptionalValue<>(
    BACKGROUND_HEADER,
    this::setBackground,
    Background::fromSpecification,
    IBackground::getSpecification);

  private final MutableValue<ContentAlignment> contentAlignment = new MutableValue<>(
    CONTENT_ALIGNMENT_HEADER,
    DEFAULT_CONTENT_POSITION,
    this::setContentAlignment,
    ContentAlignment::fromSpecification,
    Node::fromEnum);

  private final MutableOptionalValue<IControl<?, ?>> rootControl = new MutableOptionalValue<>(
    ROOT_CONTROL_HEADER,
    this::setRootControl,
    ControlFactory::createControlFromSpecification,
    IControl::getSpecification);

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
  public boolean containsControl(final IControl<?, ?> control) {
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
    return background.getValue();
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
    return contentAlignment.getValue();
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
    return opacity.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(final String internalId) {
    if (isEmpty()) {
      return Optional.empty();
    }

    final var localRootControl = getStoredRootControl();
    if (localRootControl.hasInternalId(internalId)) {
      return Optional.of(localRootControl);
    }

    return localRootControl.getOptionalStoredChildControlByInternalId(internalId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
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
  public IControl<?, ?> getStoredRootControl() {
    return rootControl.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LayerRole getRole() {
    return memberRole.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    final ILinkedList<IControl<?, ?>> childConfigurableElements = LinkedList.createEmpty();

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
    return rootControl.isEmpty();
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
  public Layer setRootControl(final IControl<?, ?> rootControl) {
    rootControl.internalSetParentLayer(this);
    this.rootControl.setValue(rootControl);

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
    rootControl.clear();
  }

  private boolean containsControlWhenContainsAny(final IControl<?, ?> control) {
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
