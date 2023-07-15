//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
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
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGuiContent;

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
	private static final WebGuiCssCreator WEB_GUI_CSS_CREATOR = new WebGuiCssCreator();
	
	//attribute
	private final MutableValue<String> title =
	new MutableValue<>(
		TITLE_HEADER,
		DEFAULT_TITLE,
		this::setTitle,
		INode::getSingleChildNodeHeader,
		Node::withChildNode
	);
	
	//attribute
	private final MutableValue<Image> icon =
	new MutableValue<>(
		ICON_HEADER,
		DEFAULT_ICON,
		this::setIcon,
		Image::fromSpecification,
		Image::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IBackground> background =
	new MutableOptionalValue<>(
		BACKGROUND_HEADER,
		this::setBackground,
		Background::fromSpecification,
		IBackground::getSpecification
	);
	
	//attribute
	private final MultiValue<ILayer<?>> layers =
	new MultiValue<>(
		LAYER_HEADER,
		this::pushLayer,
		Layer::fromSpecification,
		ILayer::getSpecification
	);
	
	//attribute
	private IFrontEndReader frontEndReader = new LocalFrontEndReader();
	
	//attribute
	private IFrontEndWriter frontEndWriter = new LocalFrontEndWriter();
	
	//optional attribute
	private IAction removeLayerAction;
	
	//constructor
	public WebGui() {
		
		reset();
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	@Override
	public void clear() {
		while (containsAny()) {
			removeLayer(getStoredTopLayer());
		}
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
	public IWebGuiContent getContent() {
		return WebGuiContent.forWebGui(this);
	}
	
	//method
	@Override
	public ICss<?, ?> getCss() {
		return WEB_GUI_CSS_CREATOR.createCssForWebGui(this);
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
	public IControl<?, ?> getStoredControlByInternalId(final String fixedId) {
		
		for (final var l : getStoredLayers()) {
			for (final var c : l.getStoredControls()) {
				if (c.hasInternalId(fixedId)) {
					return c;
				}
			}
		}
		
		throw
		InvalidArgumentException.forArgumentAndErrorPredicate(
			this,
			"does not contain a control with the given fixed id '" + fixedId + "'"
		);
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getStoredControls() {
		return getStoredLayers().toFromGroups(ILayer::getStoredControls);
	}
	
	//method
	@Override
	public IContainer<ILayer<?>> getStoredLayers() {
		return layers.getStoredValues();
	}
	
	//method
	@Override
	public ILayer<?> getStoredTopLayer() {
		return getStoredLayers().getStoredLast();
	}
	
	//method
	@Override
	public String getTitle() {
		return title.getValue();
	}
	
	//method
	@Override
	public String getTitleInQuotes() {
		return GlobalStringHelper.getInQuotes(getTitle());
	}
	
	//method
	@Override
	public boolean hasBackground() {
		return background.hasValue();
	}
	
	//method
	@Override
	public boolean hasRemoveLayerAction() {
		return (removeLayerAction != null);
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return getStoredLayers().isEmpty();
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
		
		layer.technicalSetParentGui(this);
		
		layers.add(layer);
		
		return this;
	}
	
	//method
	@Override
	public WebGui pushLayerWithRootControl(final IControl<?, ?> rootControl) {
		return pushLayer(new Layer().setRootControl(rootControl));
	}
	
	//method
	@Override
	public void removeBackground() {
		background.clear();
	}
	
	//method
	@Override
	public void removeLayer(final ILayer<?> layer) {
		
		layers.remove(layer);
		
		runProbableRemoveLayerAction();
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
		final IFrontEndWriter frontEndWriter
	) {
		
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
	public WebGui setRemoveLayerAction(IAction removeLayerAction) {
		
		GlobalValidator.assertThat(removeLayerAction).thatIsNamed("remove layer action").isNotNull();
		
		this.removeLayerAction = removeLayerAction;
		
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
		
		//An image will not be reset since an image is not supposed to be applied from a Style.
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
	
	//method
	private void runProbableRemoveLayerAction() {
		if (hasRemoveLayerAction()) {
			removeLayerAction.run();
		}
	}
}
