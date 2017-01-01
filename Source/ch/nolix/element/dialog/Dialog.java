/*
 * file:	Dialog.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	340
 */

//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.event.KeyEvent;












//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Clearable;
import ch.nolix.common.interfaces.IRequestableContainer;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.ConfigurationElement;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Title;

//class
public abstract class Dialog<D extends Dialog<D>>
extends ConfigurationElement<D>
implements
	Clearable<D>,
	IRequestableContainer
{
	//constant
	public final static String SIMPLE_CLASS_NAME = "Dialog";
	
	//default value
	public final static ContentOrientation DEFAULT_CONTENT_ORIENTATION = ContentOrientation.LeftTop;
	
	//attribute headers
	private final static String ROOT_RECTANGLE = "RootRectangle";

	//command
	private final static String REMOVE_ROOT_RECTANGLE = "RemoveRootRectangle";
		
	//attributes
	private final Title title = new Title();
	protected final BackgroundColor backgroundColor = new BackgroundColor();
	protected ContentOrientation contentOrientation = DEFAULT_CONTENT_ORIENTATION;
				
	//optional attributes
	private ILevel1Controller controller;
	private Rectangle<?, ?> rootRectangle;
	
	//multiple attribute
	private final List<Class<?>> rectangleClasses = new List<Class<?>>();
	
	public Dialog() {
		
		addRectangleClass(Area.class);
		addRectangleClass(CheckBox.class);
		addRectangleClass(HorizontalLine.class);
		addRectangleClass(VerticalLine.class);
		addRectangleClass(Label.class);
		addRectangleClass(Button.class);
		addRectangleClass(TextBox.class);
		addRectangleClass(SingleContainer.class);
		addRectangleClass(HorizontalStack.class);
		addRectangleClass(VerticalStack.class);
		addRectangleClass(TabContainer.class);
		
		setConfiguration(new DefaultDesign());
	}
	
	public final  void addRectangleClass(Class<?> rectangleClass) {
		
		Validator.throwExceptionIfValueIsNull("rectangle class", rectangleClass);
		
		if (canCreateRectangle(rectangleClass.getSimpleName())) {
			throw new RuntimeException("Rectangle created already contains an other rectanlge class with the same name as the given rectangle class.");
		}
		
		rectangleClasses.addAtEnd(rectangleClass);
	}
	
	public final boolean canCreateRectangle(String type) {
		return rectangleClasses.contains(rc -> rc.getSimpleName().equals(type));
	}
	
	//method
	/**
	 * Clears this dialog.
	 */
	@SuppressWarnings("unchecked")
	public final D clear() {
		
		removeRootRectangle();
		
		return (D)this;
	}
	
	//method
	/**
	 * @return true if this dialog contains an element with the given name
	 * @param name
	 */
	public final boolean containsElement(String name) {
		return getRefConfigurablesRecursively().contains(c -> c.hasName(name));
	}
	
	private final Rectangle<?, ?> createRectangle(String type) {
		try {
			return (Rectangle<?, ?>)(rectangleClasses.getRefFirst(rc -> rc.getSimpleName().equals(type)).newInstance());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//method
	/**
	 * @return the attributes of this dialog
	 */
	public List<Specification> getAttributes() {
		
		final List<Specification> attributes
		= super.getAttributes().addAtEnd(title.getSpecification(), backgroundColor.getSpecification());
		
		if (contentOrientation != DEFAULT_CONTENT_ORIENTATION) {
			attributes.addAtEnd(contentOrientation.getSpecification());
		}
		
		if (containsAny()) {
			attributes.addAtEnd(getRefRootRectangle().getSpecification());
		}
		
		return attributes;
	}
	
	public final ContentOrientation getContentOrientation() {
		return contentOrientation;
	}
	
	//method
	/**
	 * @return the configurable objects of this dialog
	 */
	public final List<Configurable> getRefConfigurables() {
		
		final List<Configurable> configurables = new List<Configurable>();
		
		if (hasRootRectangle()) {
			configurables.addAtEnd(getRefRootRectangle());
		}
		
		return configurables;
	}
	
	//method
	/**
	 * @param name
	 * @return the rectangle that has the given name recursively from this dialog
	 * @throws Exception if this 
	 */
	@SuppressWarnings("unchecked")
	public final <R extends Rectangle<?, ?>> R getRefRecRectangleByName(final String name) {
		return (R)getRefRootRectangle().getRefConfigurablesRecursively().getRefFirst(r -> r.hasName(name));
	}
	
	//abstract method
	/**
	 * @return the frame context of this dialog
	 */
	public abstract FrameContext getRefFrameContext();
	
	//method
	/**
	 * @return the root rectangle of this dialog
	 * @throws Exception if this dialog has no root rectangle
	 */
	public final Rectangle<?, ?> getRefRootRectangle() {
		
		if (!hasRootRectangle()) {
			throw new UnexistingAttributeException(this, "root rectangle");
		}
		
		return rootRectangle;
	}
	
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return the title of this frame
	 */
	public final String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * @return true if this dialog has a root rectangle
	 */
	public final boolean hasRootRectangle() {
		return (rootRectangle != null);
	}
	
	//method
	/**
	 * @return true if this dialog is empty
	 */
	public final boolean isEmpty() {
		return !hasRootRectangle();
	}
	
	//method
	/**
	 * Removes the root rectangle of this dialog.
	 */
	public final void removeRootRectangle() {
		rootRectangle = null;
	}
	
	//method
	/**
	 * Resets this dialog.
	 */
	public void reset() {

		//Calls method of the base class.
		super.reset();
		
		title.reset();
		removeRootRectangle();
	}
	
	//method
	/**
	 * Resets the configuration of this dialog.
	 */
	public void resetConfiguration() {
		
		setBackgroundColor(Color.WHITE_STRING);
		setContentOrientation(DEFAULT_CONTENT_ORIENTATION);
		
		if (hasRootRectangle()) {
			getRefRootRectangle().resetConfiguration();
		}
	}
	
	//method
	/**
	 * Lets this dialog run the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public void run(Statement command) {
		switch (command.getHeader()) {
			case ROOT_RECTANGLE:
				getRefRootRectangle().run(command.getNextStatement());
				break;
			case REMOVE_ROOT_RECTANGLE:
				removeRootRectangle();
				break;
			default:
				
				//Calls method of the base class.
				super.run(command);
		}
	}
	
	//method
	/**
	 * Sets the given attribute to this dialog.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		
		//Handles the case when the given attribute specifies a rectangle.
		if (canCreateRectangle(attribute.getHeader())) {
			setRootRectangle(createsAndAddsRectangle(attribute));
			return;
		}
		
		switch (attribute.getHeader()) {
			case Title.SIMPLE_CLASS_NAME:
				setTitle(attribute.getOneAttributeToString());
				break;
			case BackgroundColor.SIMPLE_CLASS_NAME:
				setBackgroundColor(attribute.getOneAttributeToString());
				break;
			case ContentOrientation.SIMPLE_CLASS_NAME:
				setContentOrientation(ContentOrientation.valueOf(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the background color of this frame.
	 * 
	 * @param backgroundColor
	 * @throws Exception if the given background color is no color name or no true color value
	 */
	public final void setBackgroundColor(String backgroundColor) {
		this.backgroundColor.setValue(backgroundColor);
	}
	
	//method
	/**
	 * Sets the layout of this frame.
	 * 
	 * @param laoyut
	 */
	@SuppressWarnings("unchecked")
	public final D setContentOrientation(ContentOrientation contentOrientation) {
		
		this.contentOrientation = contentOrientation;
		
		return (D)this;
	}
	
	//method
	/**
	 * Sets the controller of this dialog.
	 * 
	 * @param controller
	 * @throws Exception if the given controller is null
	 */
	@SuppressWarnings("unchecked")
	public final D setController(ILevel1Controller controller) {
		
		Validator.throwExceptionIfValueIsNull("controller", controller);
		
		this.controller = controller;
		
		return (D)this;
	}
	
	//method
	/**
	 * Sets the root rectangle of this dialog.
	 * 
	 * @param rootRectangle
	 * @throws Exception if:
	 *  -the given root rectangle is null
	 *  -the given root rectangle already belongs to an other dialog
	 */
	@SuppressWarnings("unchecked")
	public final D setRootRectangle(Rectangle<?, ?> rootRectangle) {
		rootRectangle.setDialog(this);
		this.rootRectangle = rootRectangle;
		
		return (D)this;
	}
	
	//method
	/**
	 * Sets the title of this frame.
	 * 
	 * @param title
	 * @throws Exception if the given title is null or an empty string
	 */
	@SuppressWarnings("unchecked")
	public final D setTitle(String title) {
		
		this.title.setValue(title);
		
		return (D)this;
	}
	
	//temp
	public abstract void update();
	
	//method
	/**
	 * Builds and returns the rectangle the given specification specifies.
	 * The built rectangle will belong to this dialog.
	 * 
	 * @param specification
	 * @return the rectangle the given specification specifies
	 * @throws Exception if the given specification is not valid
	 */
	protected final Rectangle<?, ?> createsAndAddsRectangle(Specification specification) {
		Rectangle<?, ?> rectangle = createRectangle(specification.getHeader());
		rectangle.setDialog(this);
		rectangle.addOrChangeAttributes(specification.getRefAttributes());
		return rectangle;		
	}
	
	//method
	/**
	 * @return the controller of this dialog
	 */
	protected final ILevel1Controller getRefController() {
		return controller;
	}
	
	//method
	/**
	 * Lets this dialog note a left click.
	 */
	protected void noteLeftMouseButtonPress() {
		if (hasRootRectangle()) {
			getRefRootRectangle().noteLeftMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets this dialog note a mouse move.
	 */
	protected void noteMouseMove() {
		if (hasRootRectangle()) {
			getRefRootRectangle().noteMouseMove();
		}
	}
	
	//method
	/**
	 * Lets this dialog note a pressed key.
	 * 
	 * @param keyEvent
	 */
	protected void notePressedKey(KeyEvent keyEvent) {
		if (hasRootRectangle()) {
			getRefRootRectangle().noteKeyPress(keyEvent);
		}
	}

	//method
	/**
	 * Lets this dialog note a right click.
	 */
	protected void noteRightClick() {
		if (hasRootRectangle()) {
			getRefRootRectangle().noteRightMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets this dialog note a typed key.
	 * 
	 * @param keyEvent
	 */
	protected void noteTypedKey(KeyEvent keyEvent) {
		if (hasRootRectangle()) {
			getRefRootRectangle().noteKeyPress(keyEvent);
		}
	}
	
	//method
	/**
	 * Sets the mouse position of this dialog.
	 * 
	 * @param mouseXPosition
	 * @param mouseYPosition
	 */
	protected final void setMousePosition(int mouseXPosition, int mouseYPosition) {
		if (hasRootRectangle()) {
			getRefRootRectangle().setRelativeMousePosition(mouseXPosition, mouseYPosition);
		}
	}
}
