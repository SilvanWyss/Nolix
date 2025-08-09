package ch.nolix.system.webgui.main;

import java.util.Optional;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.coreapi.web.cssmodel.ICss;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.system.element.property.MultiValueExtractor;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.gui.frontend.LocalFrontEndReader;
import ch.nolix.system.gui.frontend.LocalFrontEndWriter;
import ch.nolix.system.gui.iconresource.IconCatalog;
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
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.ILayerStack;
import ch.nolix.systemapi.webgui.main.IWebGui;

public final class WebGui //NOSONAR: A WebGui is a principal object thus it has many methods.
extends AbstractStyleElement<WebGui>
implements IWebGui<WebGui> {

  public static final String DEFAULT_TITLE = PascalCaseVariableCatalog.GUI;

  public static final Image DEFAULT_ICON = IconCatalog.NOLIX_ICON;

  public static final Color DEFAULT_BACKGROUND_COLOR = X11ColorCatalog.WHITE;

  private static final String TITLE_HEADER = PascalCaseVariableCatalog.TITLE;

  private static final String ICON_HEADER = PascalCaseVariableCatalog.ICON;

  private static final String BACKGROUND_HEADER = PascalCaseVariableCatalog.BACKGROUND;

  private static final String LAYER_HEADER = PascalCaseVariableCatalog.LAYER;

  private static final WebGuiHtmlBuilder WEB_GUI_HTML_BUILDER = new WebGuiHtmlBuilder();

  private static final WebGuiCssBuilder WEB_GUI_CSS_BUILDER = new WebGuiCssBuilder();

  private final MutableValue<String> title = new MutableValue<>(
    TITLE_HEADER,
    DEFAULT_TITLE,
    this::setTitle,
    INode::getSingleChildNodeHeader,
    Node::withChildNode);

  private final MutableValue<Image> icon = new MutableValue<>(
    ICON_HEADER,
    DEFAULT_ICON,
    this::setIcon,
    Image::fromSpecification,
    Image::getSpecification);

  private final MutableOptionalValue<IBackground> background = new MutableOptionalValue<>(
    BACKGROUND_HEADER,
    this::setBackground,
    Background::fromSpecification,
    IBackground::getSpecification);

  @SuppressWarnings("unused")
  private final MultiValueExtractor<ILayer<?>> layerExtractor = new MultiValueExtractor<>(
    LAYER_HEADER,
    this::pushLayer,
    this::getStoredLayers,
    Layer::fromSpecification,
    ILayer::getSpecification);

  private final ILayerStack layerStack = LayerStack.forWebGui(this);

  private IFrontEndReader frontEndReader = new LocalFrontEndReader();

  private IFrontEndWriter frontEndWriter = new LocalFrontEndWriter();

  public WebGui() {

    reset();

    setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
  }

  //mehtod
  @Override
  public boolean containsControl(final IControl<?, ?> control) {
    return layerStack.containsControl(control);
  }

  @Override
  public void clear() {
    layerStack.clear();
  }

  @Override
  public IFrontEndReader fromFrontEnd() {
    return frontEndReader;
  }

  @Override
  public IBackground getBackground() {
    return background.getValue();
  }

  @Override
  public IColor getBackgroundColor() {
    return getBackground().getColor();
  }

  @Override
  public IColorGradient getBackgroundColorGradient() {
    return getBackground().getColorGradient();
  }

  @Override
  public IImage getBackgroundImage() {
    return getBackground().getImage();
  }

  @Override
  public ImageApplication getBackgroundImageApplication() {
    return getBackground().getImageApplication();
  }

  @Override
  public BackgroundType getBackgroundType() {
    return getBackground().getType();
  }

  @Override
  public ICss getCss() {
    return WEB_GUI_CSS_BUILDER.createCssForWebGui(this);
  }

  @Override
  public IHtmlElement getHtml() {
    return WEB_GUI_HTML_BUILDER.createHtmlForWebGui(this);
  }

  @Override
  public IImage getIcon() {
    return icon.getValue();
  }

  @Override
  public IContainer<IHtmlElementEvent> getHtmlElementEventRegistrations() {

    final ILinkedList<IHtmlElementEvent> htmlElementEventRegistrations = LinkedList.createEmpty();

    registerHtmlElementEventsAt(htmlElementEventRegistrations);

    return htmlElementEventRegistrations;
  }

  @Override
  public int getLayerCount() {
    return layerStack.getLayerCount();
  }

  @Override
  public Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(final String internalId) {
    return layerStack.getOptionalStoredControlByInternalId(internalId);
  }

  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredLayers();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
    return layerStack.getStoredControls();
  }

  @Override
  public IContainer<ILayer<?>> getStoredLayers() {
    return layerStack.getStoredLayers();
  }

  @Override
  public ILayer<?> getStoredTopLayer() {
    return layerStack.getStoredTopLayer();
  }

  @Override
  public String getTitle() {
    return title.getValue();
  }

  @Override
  public boolean hasBackground() {
    return background.containsAny();
  }

  @Override
  public boolean hasRemoveLayerAction() {
    return layerStack.hasRemoveLayerAction();
  }

  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return layerStack.isEmpty();
  }

  @Override
  public boolean isRootGui() {
    return false;
  }

  @Override
  public IFrontEndWriter onFrontEnd() {
    return frontEndWriter;
  }

  @Override
  public WebGui pushLayer(final ILayer<?> layer) {

    layerStack.pushLayer(layer);

    return this;
  }

  @Override
  public WebGui pushLayerWithRootControl(final IControl<?, ?> rootControl) {

    layerStack.pushLayerWithRootControl(rootControl);

    return this;
  }

  @Override
  public void removeBackground() {
    background.clear();
  }

  @Override
  public void removeLayer(final ILayer<?> layer) {
    layerStack.removeLayer(layer);
  }

  public WebGui setBackground(final IBackground background) {

    this.background.setValue(background);

    return this;
  }

  @Override
  public WebGui setBackgroundColor(final IColor backgroundColor) {
    return setBackground(Background.withColor(backgroundColor));
  }

  @Override
  public WebGui setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
    return setBackground(Background.withColorGradient(backgroundColorGradient));
  }

  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage) {
    return setBackground(Background.withImage(backgroundImage));
  }

  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
    return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

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

  @Override
  public WebGui setIcon(final IImage icon) {

    this.icon.setValue(Image.fromAnyImage(icon));

    return this;
  }

  @Override
  public WebGui setRemoveLayerAction(Runnable removeLayerAction) {

    layerStack.setRemoveLayerAction(removeLayerAction);

    return this;
  }

  @Override
  public WebGui setTitle(final String title) {

    Validator.assertThat(title).thatIsNamed(LowerCaseVariableCatalog.TITLE).isNotBlank();

    this.title.setValue(title);

    return this;
  }

  @Override
  protected void resetConfigurationElement() {

    setTitle(DEFAULT_TITLE);
    setIcon(DEFAULT_ICON);

    clear();
  }

  @Override
  protected void resetStyle() {

    //An image will not be reset since an image is not supposed to be applied from
    //a Style.
    if (hasBackground() && getBackground().getType() != BackgroundType.IMAGE) {
      setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    }
  }

  private void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> htmlElementEventRegistrations) {
    for (final var c : getStoredControls()) {
      c.registerHtmlElementEventsAt(htmlElementEventRegistrations);
    }
  }
}
