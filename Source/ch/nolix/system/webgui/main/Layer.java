//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.base.StylableElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.gui.canvas.Background;
import ch.nolix.system.webgui.helper.LayerHTMLHelper;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;
import ch.nolix.systemapi.guiapi.widgetguiapi.LayerRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public final class Layer extends StylableElement<Layer> implements ILayer<Layer> {
	
	//static method
	public static Layer fromSpecification(final INode<?> specification) {
		
		final var layer = new Layer();
		layer.resetFromSpecification(specification);
		
		return layer;
	}
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//constant
	private static final String BACKGROUND_HEADER = PascalCaseCatalogue.BACKGROUND;
	
	//constant
	private static final String ROOT_CONTROL_HEADER = "RootControl";
	
	//attribute
	private final MutableOptionalValue<LayerRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		LayerRole::fromSpecification,
		Node::fromEnum
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
	private final MutableOptionalValue<IControl<?, ?>> rootControl =
	new MutableOptionalValue<>(
		ROOT_CONTROL_HEADER,
		this::setRootControl,
		GlobalControlFactory::createControlFromSpecification,
		IControl::getSpecification
	);
	
	//optional attribute
	private IWebGUI<?> parentGUI;
	
	//method
	@Override
	public boolean belongsToGUI() {
		return (parentGUI != null);
	}
	
	//method
	@Override
	public void clear() {
		if (containsAny()) {
			clearWhenIsNotEmpty();
		}
	}
	
	//method
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
	public IWebGUI<?> getRefParentGUI() {
		
		assertBelongsToGUI();
		
		return parentGUI;
	}
	
	//method
	@Override
	public IControl<?, ?> getRefRootControl() {
		return rootControl.getValue();
	}
	
	//method
	@Override
	public LayerRole getRole() {
		return role.getValue();
	}
	
	//method
	@Override
	public IContainer<? extends IStylableElement<?>> getRefChildStylableElements() {
		
		final var childConfigurableElements = new LinkedList<IControl<?, ?>>();
		
		if (containsAny()) {
			childConfigurableElements.addAtEnd(getRefRootControl());
		}
		
		return childConfigurableElements;
	}
	
	//method
	@Override
	public boolean hasRole() {
		return role.hasValue();
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
	public void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {
		//Does nothing.
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
	public void removeSelfFromGUI() {
		getRefParentGUI().removeLayer(this);
		parentGUI = null;
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
	public Layer setRole(final LayerRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return LayerHTMLHelper.INSTANCE.getHTMLElementOfLayer(this);
	}
	
	//method
	@Override
	public String toHTMLString() {
		return toHTMLElement().toString();
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
	private void assertBelongsToGUI() {
		if (!belongsToGUI()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, IWebGUI.class);
		}
	}
	
	//method
	private void clearWhenIsNotEmpty() {
		rootControl.clear();
	}
}
