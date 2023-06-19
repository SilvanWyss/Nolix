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
import ch.nolix.coreapi.webapi.cssapi.ICSS;
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
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUIContent;

//class
public final class WebGUI extends StyleElement<WebGUI> implements IWebGUI<WebGUI> {
	
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
	public WebGUI() {
		
		reset();
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	@Override
	public void clear() {
		while (containsAny()) {
			removeLayer(getOriTopLayer());
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
	public IWebGUIContent getContent() {
		return WebGUIContent.forWebGUI(this);
	}
	
	//method
	@Override
	public ICSS<?, ?> getCSS() {
		return WebGUICSSCreator.INSTANCE.createCSSForWebGUI(this);
	}
	
	//method
	@Override
	public IImage getIcon() {
		return icon.getValue();
	}
	
	//method
	@Override
	public IContainer<IHTMLElementEvent> getHTMLElementEventRegistrations() {
		
		final var lHTMLElementEventRegistrations = new LinkedList<IHTMLElementEvent>();
		
		registerHTMLElementEventsAt(lHTMLElementEventRegistrations);
		
		return lHTMLElementEventRegistrations;
	}

	//method
	@Override
	public IContainer<? extends IStylableElement<?>> getOriChildStylableElements() {
		return getOriLayers();
	}
	
	//method
	@Override
	public IControl<?, ?> getOriControlByInternalId(final String fixedId) {
		
		for (final var l : getOriLayers()) {
			for (final var c : l.getOriControls()) {
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
	public IContainer<IControl<?, ?>> getOriControls() {
		return getOriLayers().toFromGroups(ILayer::getOriControls);
	}
	
	//method
	@Override
	public IContainer<ILayer<?>> getOriLayers() {
		return layers.getOriValues();
	}
	
	//method
	@Override
	public ILayer<?> getOriTopLayer() {
		return getOriLayers().getOriLast();
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
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return getOriLayers().isEmpty();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	@Override
	public IFrontEndWriter onFrontEnd() {
		return frontEndWriter;
	}
	
	//method
	@Override
	public WebGUI pushLayer(final ILayer<?> layer) {
		
		layer.technicalSetParentGUI(this);
		
		layers.add(layer);
		
		return this;
	}
	
	//method
	@Override
	public WebGUI pushLayerWithRootControl(final IControl<?, ?> rootControl) {
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
		
		//TODO: Beautify this code.
		if (removeLayerAction != null) {
			removeLayerAction.run();
		}
	}
	
	//method
	public WebGUI setBackground(final IBackground background) {
		
		this.background.setValue(background);
		
		return this;
	}
	
	//method
	@Override
	public WebGUI setBackgroundColor(final IColor backgroundColor) {
		return setBackground(Background.withColor(backgroundColor));
	}
	
	//method
	@Override
	public WebGUI setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
		return setBackground(Background.withColorGradient(backgroundColorGradient));
	}
	
	//method
	@Override
	public WebGUI setBackgroundImage(final IImage backgroundImage) {
		return setBackground(Background.withImage(backgroundImage));
	}
	
	//method
	@Override
	public WebGUI setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
		return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
	}
	
	//method
	@Override
	public WebGUI setFrontEndReaderAndFrontEndWriter(
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
	public WebGUI setIcon(final IImage icon) {
		
		this.icon.setValue(Image.fromAnyImage(icon));
		
		return this;
	}
	
	//method
	@Override
	public WebGUI setRemoveLayerAction(IAction removeLayerAction) {
		
		GlobalValidator.assertThat(removeLayerAction).thatIsNamed("remove layer action").isNotNull();
		
		this.removeLayerAction = removeLayerAction;
		
		return this;
	}
	
	//method
	@Override
	public WebGUI setTitle(final String title) {
		
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
	private void registerHTMLElementEventsAt(final LinkedList<IHTMLElementEvent> lHTMLElementEventRegistrations) {
		for (final var c : getOriControls()) {
			c.registerHTMLElementEventsAt(lHTMLElementEventRegistrations);
		}
	}
}
