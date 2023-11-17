//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.element.property.MultiValueExtractor;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.element.style.StyleElement;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.gui.canvas.Background;
import ch.nolix.system.gui.frontend.LocalFrontEndReader;
import ch.nolix.system.gui.frontend.LocalFrontEndWriter;
import ch.nolix.system.gui.iconresource.IconCatalogue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.canvasapi.IBackground;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.ILayerStack;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGui extends StyleElement<WebGui> implements IWebGui<WebGui> {

  //constant
  public static final String DEFAULT_TITLE = PascalCaseCatalogue.GUI;

  //constant
  public static final Image DEFAULT_ICON = IconCatalogue.NOLIX_ICON;

  //constant
  public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

  //constant
  private static final String TITLE_HEADER = PascalCaseCatalogue.TITLE;

  //constant
  private static final String ICON_HEADER = PascalCaseCatalogue.ICON;

  //constant
  private static final String BACKGROUND_HEADER = PascalCaseCatalogue.BACKGROUND;

  //constant
  private static final String LAYER_HEADER = PascalCaseCatalogue.LAYER;

  //constant
  private static final WebGuiHtmlBuilder WEB_GUI_HTML_BUILDER = new WebGuiHtmlBuilder();

  //constant
  private static final WebGuiCssBuilder WEB_GUI_CSS_BUILDER = new WebGuiCssBuilder();

  //attribute
  private final MutableValue<String> title = new MutableValue<>(
    TITLE_HEADER,
    DEFAULT_TITLE,
    this::setTitle,
    INode::getSingleChildNodeHeader,
    Node::withChildNode);

  //attribute
  private final MutableValue<Image> icon = new MutableValue<>(
    ICON_HEADER,
    DEFAULT_ICON,
    this::setIcon,
    Image::fromSpecification,
    Image::getSpecification);

  //attribute
  private final MutableOptionalValue<IBackground> background = new MutableOptionalValue<>(
    BACKGROUND_HEADER,
    this::setBackground,
    Background::fromSpecification,
    IBackground::getSpecification);

  //attribute
  @SuppressWarnings("unused")
  private final MultiValueExtractor<ILayer<?>> layerExtractor = new MultiValueExtractor<>(
    LAYER_HEADER,
    this::pushLayer,
    this::getStoredLayers,
    Layer::fromSpecification,
    ILayer::getSpecification);

  //attribute
  private final ILayerStack layerStack = LayerStack.forWebGui(this);

  //attribute
  private IFrontEndReader frontEndReader = new LocalFrontEndReader();

  //attribute
  private IFrontEndWriter frontEndWriter = new LocalFrontEndWriter();

  //constructor
  public WebGui() {

    reset();

    setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
  }

  //method
  @Override
  public void clear() {
    layerStack.clear();
  }

  //method
  @Override
  public IFrontEndReader fromFrontEnd() {
    return frontEndReader;
  }

  //method
  @Override
  public IBackground getBackground() {
    return background.getValue();
  }

  //method
  @Override
  public IColor getBackgroundColor() {
    return getBackground().getColor();
  }

  //method
  @Override
  public IColorGradient getBackgroundColorGradient() {
    return getBackground().getColorGradient();
  }

  //method
  @Override
  public IImage getBackgroundImage() {
    return getBackground().getImage();
  }

  //method
  @Override
  public ImageApplication getBackgroundImageApplication() {
    return getBackground().getImageApplication();
  }

  //method
  @Override
  public BackgroundType getBackgroundType() {
    return getBackground().getType();
  }

  //method
  @Override
  public ICss getCss() {
    return WEB_GUI_CSS_BUILDER.createCssForWebGui(this);
  }

  //method
  @Override
  public IHtmlElement getHtml() {
    return WEB_GUI_HTML_BUILDER.createHtmlForWebGui(this);
  }

  //method
  @Override
  public IImage getIcon() {
    return icon.getValue();
  }

  //method
  @Override
  public IContainer<IHtmlElementEvent> getHtmlElementEventRegistrations() {

    final var htmlElementEventRegistrations = new LinkedList<IHtmlElementEvent>();

    registerHtmlElementEventsAt(htmlElementEventRegistrations);

    return htmlElementEventRegistrations;
  }

  //method
  @Override
  public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
    return getStoredLayers();
  }

  //method
  @Override
  public IControl<?, ?> getStoredControlOrNullByInternalId(final String internalId) {
    return layerStack.getStoredControlOrNullByInternalId(internalId);
  }

  //method
  @Override
  public IContainer<IControl<?, ?>> getStoredControls() {
    return layerStack.getStoredControls();
  }

  //method
  @Override
  public IContainer<ILayer<?>> getStoredLayers() {
    return layerStack.getStoredLayers();
  }

  //method
  @Override
  public ILayer<?> getStoredTopLayer() {
    return layerStack.getStoredTopLayer();
  }

  //method
  @Override
  public String getTitle() {
    return title.getValue();
  }

  //method
  @Override
  public boolean hasBackground() {
    return background.containsAny();
  }

  //method
  @Override
  public boolean hasRemoveLayerAction() {
    return layerStack.hasRemoveLayerAction();
  }

  //method
  @Override
  public boolean hasRole(final String role) {
    return false;
  }

  //method
  @Override
  public boolean isEmpty() {
    return layerStack.isEmpty();
  }

  //method
  @Override
  public boolean isRootGui() {
    return false;
  }

  //method
  @Override
  public IFrontEndWriter onFrontEnd() {
    return frontEndWriter;
  }

  //method
  @Override
  public WebGui pushLayer(final ILayer<?> layer) {

    layerStack.pushLayer(layer);

    return this;
  }

  //method
  @Override
  public WebGui pushLayerWithRootControl(final IControl<?, ?> rootControl) {

    layerStack.pushLayerWithRootControl(rootControl);

    return this;
  }

  //method
  @Override
  public void removeBackground() {
    background.clear();
  }

  //method
  @Override
  public void removeLayer(final ILayer<?> layer) {
    layerStack.removeLayer(layer);
  }

  //method
  public WebGui setBackground(final IBackground background) {

    this.background.setValue(background);

    return this;
  }

  //method
  @Override
  public WebGui setBackgroundColor(final IColor backgroundColor) {
    return setBackground(Background.withColor(backgroundColor));
  }

  //method
  @Override
  public WebGui setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
    return setBackground(Background.withColorGradient(backgroundColorGradient));
  }

  //method
  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage) {
    return setBackground(Background.withImage(backgroundImage));
  }

  //method
  @Override
  public WebGui setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
    return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  //method
  @Override
  public WebGui setFrontEndReaderAndFrontEndWriter(
    final IFrontEndReader frontEndReader,
    final IFrontEndWriter frontEndWriter) {

    GlobalValidator.assertThat(frontEndReader).thatIsNamed(IFrontEndReader.class).isNotNull();
    GlobalValidator.assertThat(frontEndWriter).thatIsNamed(IFrontEndWriter.class).isNotNull();

    this.frontEndReader = frontEndReader;
    this.frontEndWriter = frontEndWriter;

    return this;
  }

  //method
  @Override
  public WebGui setIcon(final IImage icon) {

    this.icon.setValue(Image.fromAnyImage(icon));

    return this;
  }

  //method
  @Override
  public WebGui setRemoveLayerAction(Runnable removeLayerAction) {

    layerStack.setRemoveLayerAction(removeLayerAction);

    return this;
  }

  //method
  @Override
  public WebGui setTitle(final String title) {

    GlobalValidator.assertThat(title).thatIsNamed(LowerCaseCatalogue.TITLE).isNotBlank();

    this.title.setValue(title);

    return this;
  }

  //method
  @Override
  protected void resetConfigurationElement() {

    setTitle(DEFAULT_TITLE);
    setIcon(DEFAULT_ICON);

    clear();
  }

  //method
  @Override
  protected void resetStyle() {

    //An image will not be reset since an image is not supposed to be applied from
    //a Style.
    if (hasBackground() && getBackground().getType() != BackgroundType.IMAGE) {
      setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    }
  }

  //method
  private void registerHtmlElementEventsAt(final LinkedList<IHtmlElementEvent> htmlElementEventRegistrations) {
    for (final var c : getStoredControls()) {
      c.registerHtmlElementEventsAt(htmlElementEventRegistrations);
    }
  }
}
