//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.gui.canvas.Background;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.canvasapi.IBackground;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;
import ch.nolix.systemapi.guiapi.structureproperty.ContentAlignment;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class Layer extends StylableElement<Layer> implements ILayer<Layer> {
	
	//constant
	public static final double DEFAULT_OPACITY = 1.0;
	
	//constant
	public static final IColor DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//constant
	public static final ContentAlignment DEFAULT_CONTENT_POSITION = ContentAlignment.TOP;
	
	//static method
	public static Layer fromSpecification(final INode<?> specification) {
		
		final var layer = new Layer();
		layer.resetFromSpecification(specification);
		
		return layer;
	}
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String OPACITY_HEADER = PascalCaseCatalogue.OPACITY;
	
	//constant
	private static final String BACKGROUND_HEADER = PascalCaseCatalogue.BACKGROUND;
	
	//constant
	private static final String CONTENT_ALIGNMENT_HEADER = "ContentAlignment";
	
	//constant
	private static final String ROOT_CONTROL_HEADER = "RootControl";
	
	//constant
	private static final LayerHtmlBuilder HTML_CREATOR = new LayerHtmlBuilder();
	
	//constant
	private static final LayerCssBuilder CSS_RULE_CREATOR = new LayerCssBuilder();
	
	//attribute
	//An id works correctly for CSS only when it begins with a letter.
	private final String fixedId = "i" + GlobalIdCreator.createIdOf10HexadecimalCharacters();
	
	//attribute
	private final MutableOptionalValue<LayerRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		LayerRole::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final MutableValue<Double> opacity =
	new MutableValue<>(
		OPACITY_HEADER,
		DEFAULT_OPACITY,
		this::setOpacity,
		s -> getOpacityFromString(s.getSingleChildNodeHeader()),
		Node::withChildNode
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
	private final MutableValue<ContentAlignment> contentAlignment =
	new MutableValue<>(
		CONTENT_ALIGNMENT_HEADER,
		DEFAULT_CONTENT_POSITION,
		this::setContentAlignment,
		ContentAlignment::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final MutableOptionalValue<IControl<?, ?>> rootControl =
	new MutableOptionalValue<>(
		ROOT_CONTROL_HEADER,
		this::setRootControl,
		GlobalControlFactory::createControlFromSpecification,
		IControl::getSpecification
	);
	
	//optional attribute
	private IWebGui<?> parentGui;
	
	//constructor
	public Layer() {
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	@Override
	public boolean belongsToGui() {
		return (parentGui != null);
	}
	
	//method
	@Override
	public void clear() {
		if (containsAny()) {
			clearWhenIsNotEmpty();
		}
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
	public ContentAlignment getContentAlignment() {
		return contentAlignment.getValue();
	}
	
	//method
	@Override
	public ICssRule getCssRule() {
		return CSS_RULE_CREATOR.getCssRuleForLayer(this);
	}
	
	//method
	@Override
	public IHtmlElement getHtml() {
		return HTML_CREATOR.getHtmlElementForLayer(this);
	}
	
	//method
	@Override
	public String getInternalId() {
		return fixedId;
	}
	
	//method
	@Override
	public double getOpacity() {
		return opacity.getValue();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getStoredControls() {
		
		if (isEmpty()) {
			return getStoredControlsWhenIsEmpty();
		}
		
		return getStoredControlsWhenIsNotEmpty();
	}
	
	//method
	@Override
	public IWebGui<?> getStoredParentGui() {
		
		assertBelongsToGui();
		
		return parentGui;
	}
	
	//method
	@Override
	public IControl<?, ?> getStoredRootControl() {
		return rootControl.getValue();
	}
	
	//method
	@Override
	public LayerRole getRole() {
		return role.getValue();
	}
	
	//method
	@Override
	public IContainer<? extends IStylableElement<?>> getStoredChildStylableElements() {
		
		final var childConfigurableElements = new LinkedList<IControl<?, ?>>();
		
		if (containsAny()) {
			childConfigurableElements.addAtEnd(getStoredRootControl());
		}
		
		return childConfigurableElements;
	}
	
	//method
	@Override
	public boolean hasBackground() {
		return background.containsAny();
	}
	
	//method
	@Override
	public boolean hasInternalId(final String fixedId) {
		return getInternalId().equals(fixedId);
	}
	
	//method
	@Override
	public boolean hasRole() {
		return role.containsAny();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (rootControl == null);
	}
	
	//method
	@Override
	public void removeBackground() {
		background.clear();
	}
	
	//method
	public void removeRole() {
		role.clear();
	}
	
	//method
	@Override
	public void removeSelfFromGui() {
		getStoredParentGui().removeLayer(this);
		parentGui = null;
	}
	
	//method
	public Layer setBackground(final IBackground background) {
		
		this.background.setValue(background);
		
		return this;
	}
	
	//method
	@Override
	public Layer setBackgroundColor(final IColor backgroundColor) {
		return setBackground(Background.withColor(backgroundColor));
	}
	
	//method
	@Override
	public Layer setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
		return setBackground(Background.withColorGradient(backgroundColorGradient));
	}
	
	//method
	@Override
	public Layer setBackgroundImage(final IImage backgroundImage) {
		return setBackground(Background.withImage(backgroundImage));
	}
	
	//method
	@Override
	public Layer setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
		return setBackground(Background.withImageAndImageApplication(backgroundImage, imageApplication));
	}
	
	//method
	@Override
	public Layer setRootControl(final IControl<?, ?> rootControl) {
		
		rootControl.technicalSetParentLayer(this);		
		this.rootControl.setValue(rootControl);
		
		return this;
	}
	
	//method
	@Override
	public Layer setContentAlignment(final ContentAlignment contentAlignment) {
		
		this.contentAlignment.setValue(contentAlignment);
		
		return this;
	}
	
	//method
	@Override
	public Layer setOpacity(final double opacity) {
		
		GlobalValidator.assertThat(opacity).thatIsNamed("opacity").isBetween(0.0, 1.0);
		
		this.opacity.setValue(opacity);
		
		return this;
	}
	
	//method
	@Override
	public Layer setRole(final LayerRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	@Override
	public void technicalSetParentGui(final IWebGui<?> parentGui) {
		
		GlobalValidator.assertThat(parentGui).thatIsNamed("parent GUI").isNotNull();
		assertDoesNotBelongToGui();
		
		this.parentGui = parentGui;
	}
		
	//method
	@Override
	protected void resetStylableElement() {
		removeRole();
		clear();
	}
	
	//method
	@Override
	protected void resetStyle() {
		removeBackground();
	}
	
	//method
	private void assertBelongsToGui() {
		if (!belongsToGui()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, IWebGui.class);
		}
	}
	
	//method
	private void assertDoesNotBelongToGui() {
		if (belongsToGui()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentGui());
		}
	}
	
	//method
	private void clearWhenIsNotEmpty() {
		rootControl.clear();
	}
	
	//method
	private void fillUpChildControlsOfControlIntoListRecursively(
		final IControl<?, ?> control,
		final LinkedList<IControl<?, ?>> list
	) {
		
		final var childControls = control.getStoredChildControls();
		
		list.addAtEnd(childControls);
		childControls.forEach(cc -> fillUpChildControlsOfControlIntoListRecursively(cc, list));
	}
	
	//method
	private double getOpacityFromString(final String string) {
		
		GlobalValidator.assertThat(string).thatIsNamed(String.class).isNotNull();
		
		if (!string.endsWith("%")) {
			return Double.valueOf(string);
		}
		
		return (Double.valueOf(string.substring(0, string.length() - 1)) / 100);
	}
	
	//method
	private IContainer<IControl<?, ?>> getStoredControlsWhenIsEmpty() {
		return new ImmutableList<>();
	}
	
	//method
	private IContainer<IControl<?, ?>> getStoredControlsWhenIsNotEmpty() {
		
		final var controls = new LinkedList<IControl<?, ?>>();
		controls.addAtEnd(getStoredRootControl());
		fillUpChildControlsOfControlIntoListRecursively(getStoredRootControl(), controls);
		
		return controls;
	}
}
