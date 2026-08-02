/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import java.util.Optional;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.ImmutableImage;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.gui.iconresource.IconCatalog;
import ch.nolix.system.property.proxy.MultiValueProxy;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.style.stylable.AbstractStyleElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.BackgroundType;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;
import ch.nolix.systemapi.style.stylable.IStylableElement;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.ILayerStack;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebGui // NOSONAR: A WebGui is a principal object thus it has many methods.
extends AbstractStyleElement<WebGui>
implements IWebGui<WebGui> {
  public static final String DEFAULT_TITLE = PascalCaseVariableNameCatalog.GUI;

  public static final ImmutableImage DEFAULT_ICON = IconCatalog.NOLIX_ICON;

  public static final Color DEFAULT_BACKGROUND_COLOR = X11ColorCatalog.WHITE;

  private static final String TITLE_HEADER = PascalCaseVariableNameCatalog.TITLE;

  private static final String ICON_HEADER = PascalCaseVariableNameCatalog.ICON;

  private static final String BACKGROUND_HEADER = PascalCaseVariableNameCatalog.BACKGROUND;

  private static final String LAYER_HEADER = PascalCaseVariableNameCatalog.LAYER;

  private final Value<String> title = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    TITLE_HEADER,
    DEFAULT_TITLE,
    this::setTitle,
    INode::getSingleChildNodeHeader,
    ImmutableNode::withChildNodes);

  private final Value<ImmutableImage> icon = //
  Value.withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    ICON_HEADER,
    DEFAULT_ICON,
    this::setIcon,
    ImmutableImage::fromSpecification,
    ImmutableImage::getSpecification);

  private final OptionalValue<IBackground> background = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    BACKGROUND_HEADER,
    this::setBackground,
    Background::fromSpecification,
    IBackground::getSpecification);

  @SuppressWarnings("unused")
  private final MultiValueProxy<ILayer> layerExtractor = //
  MultiValueProxy.withNameAndAdderAndGetterAndValueMapperAndSpecificationMapper(
    LAYER_HEADER,
    this::pushLayer,
    this::getStoredLayers,
    Layer::fromSpecification,
    ILayer::getSpecification);

  private final ILayerStack layerStack = LayerStack.forWebGui(this);

  private IFrontEndReader frontEndReader;

  private IFrontEndWriter frontEndWriter;

  public WebGui() {
    reset();

    setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
  }

  @Override
  public boolean containsControl(final Control<?, ?> control) {
    return layerStack.containsControl(control);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    layerStack.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFrontEndReader fromFrontEnd() {
    return frontEndReader;
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
  public ICss getCss() {
    return WebGuiCssBuilder.createCssForWebGui(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement getHtml() {
    return WebGuiHtmlBuilder.createHtmlForWebGui(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IImage getIcon() {
    return icon.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<IHtmlElementEvent> getHtmlElementEventRegistrations() {
    final ILinkedList<IHtmlElementEvent> htmlElementEventRegistrations = LinkedList.createEmpty();

    registerHtmlElementEventsAt(htmlElementEventRegistrations);

    return htmlElementEventRegistrations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLayerCount() {
    return layerStack.getLayerCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Control<?, ?>> getOptionalStoredControlByInternalId(final String internalId) {
    return layerStack.getOptionalStoredControlByInternalId(internalId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredLayers();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredControls() {
    return layerStack.getStoredControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ILayer> getStoredLayers() {
    return layerStack.getStoredLayers();
  }

  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return layerStack.getStoredStructureControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILayer getStoredTopLayer() {
    return layerStack.getStoredTopLayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTitle() {
    return title.getStoredValue();
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
  public boolean hasRemoveLayerAction() {
    return layerStack.hasRemoveLayerAction();
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
  public boolean isEmpty() {
    return layerStack.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFrontEndWriter onFrontEnd() {
    return frontEndWriter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui pushLayer(final ILayer layer) {
    layerStack.pushLayer(layer);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui pushLayerWithRootControl(final Control<?, ?> rootControl) {
    layerStack.pushLayerWithRootControl(rootControl);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeBackground() {
    background.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeLayer(final ILayer layer) {
    layerStack.removeLayer(layer);
  }

  public WebGui setBackground(final IBackground background) {
    this.background.setValue(background);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setBackgroundColor(final IColor backgroundColor) {
    return setBackground(Background.withColor(backgroundColor));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
    return setBackground(Background.withColorGradient(backgroundColorGradient));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage) {
    return setBackground(Background.withImage(backgroundImage));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
    return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setFrontEndReaderAndFrontEndWriter(
    final IFrontEndReader frontEndReader,
    final IFrontEndWriter frontEndWriter) {
    Validator.assertThat(frontEndReader).thatIsNamed(IFrontEndReader.class).isNotNull();
    Validator.assertThat(frontEndWriter).thatIsNamed(IFrontEndWriter.class).isNotNull();

    this.frontEndReader = frontEndReader;
    this.frontEndWriter = frontEndWriter;

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setIcon(final IImage icon) {
    this.icon.setValue(ImmutableImage.fromAnyImage(icon));

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setRemoveLayerAction(Runnable removeLayerAction) {
    layerStack.setRemoveLayerAction(removeLayerAction);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WebGui setTitle(final String title) {
    Validator.assertThat(title).thatIsNamed(LowerCaseVariableNameCatalog.TITLE).isNotBlank();

    this.title.setValue(title);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetConfigurationElement() {
    setTitle(DEFAULT_TITLE);
    setIcon(DEFAULT_ICON);

    clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetStyle() {
    // An image will not be reset since an image is not supposed to be applied from
    // a Style.
    if (hasBackground() && getBackground().getType() != BackgroundType.IMAGE) {
      setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    }
  }

  private void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> htmlElementEventRegistrations) {
    for (final var c : getStoredStructureControls()) {
      c.registerHtmlElementEventsAt(htmlElementEventRegistrations);
    }
  }
}
