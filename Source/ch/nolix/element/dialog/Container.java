/*
 * file:	Container.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	110
 */

//package declaration
package ch.nolix.element.dialog;

//java import
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;

//class
/**
 * A container is a rectangle that contains other rectangles.
 */
public abstract class Container<C extends Container<C, ?>, BRS extends BorderableRectangleStructure<?>>
extends BorderableRectangle<C, BRS> {
	
	//attribute
	private ContainerRole role;

	//constructor
	/**
	 * Creates new container with the given structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 * @param hoverFocusStructure
	 */
	public Container(
		BRS normalStructure,
		BRS hoverStructure,
		BRS focusStructure) {
		
		//Calls constructor of the base class.
		super(normalStructure, hoverStructure, focusStructure);
	}
	
	public List<Specification> getAttributes() {
		
		List<Specification> attributes = super.getAttributes();
		
		if (hasRole()) {
			attributes.addAtEnd(new Specification("Role", role.toString()));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the configurable objects of this container
	 */
	public final List<Configurable> getRefConfigurables() {
		
		List<Configurable> configurables = new List<Configurable>();
		
		getRefRectangles().forEach(r -> configurables.addAtEnd(r));
		
		return configurables;
	}
	
	//abstract method
	/**
	 * @return the rectangles of this container
	 */
	public abstract List<Rectangle<?, ?>> getRefRectangles();
	
	//abstract method
	/**
	 * @return the rectangles of this container that are shown
	 */
	public abstract List<Rectangle<?, ?>> getRefShownRectangles();
	
	public final boolean hasRole() {
		return (role != null);
	}
	
	public final boolean hasRole(final String role) {
			
		if (hasRole()) {
			return role.equals(this.role.toString());
		}
		
		return false;
	}
	
	//method
	/**
	 * Resets the configuration of this container.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		//Resets the configuration of the rectangles of this container.
		getRefRectangles().forEach(r -> r.resetConfiguration());
	}
	
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
		case "Role":
			setRole(ContainerRole.valueOf(attribute.getOneAttributeToString()));
			break;
		default:
			
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the role of this container.
	 * 
	 * @param role
	 * @return this container
	 * @throws Exception if the given role is null
	 */
	@SuppressWarnings("unchecked")
	public final C setRole(final ContainerRole role) {
		
		//Checks the given role.
		Validator.throwExceptionIfValueIsNull("role", role);
		
		this.role = role;
		
		return (C)this;
	}
	
	//method
	/**
	 * Lets this container note a left click.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		//Lets the rectangles of this stack that are shown note a left click.
		getRefShownRectangles().forEach(r -> r.noteLeftMouseButtonPress());
	}
	
	//method
	/**
	 * Lets this container note a mouse move.
	 */
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		//Lets the shown rectangles of this contianer note a mouse move.
		getRefShownRectangles().forEach(r -> r.noteMouseMove());
	}
	
	//method
	/**
	 * Lets this container note the given key event that has been created by a key pressing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(KeyEvent keyEvent) {
					
		//Lets the shown rectangles of this container note the given key event that has been created by a key pressing.
		getRefShownRectangles().forEach(r -> r.noteKeyPress(keyEvent));
	}
	
	//method
	/**
	 * Lets this container note a right click
	 */
	public void noteRightMouseButtonPress() {
		
		//Lets the shown rectangles of this container note a right click.
		getRefShownRectangles().forEach(r -> r.noteRightMouseButtonPress());
	}
	
	//method
	/**
	 * Lets this container note the given key event that has been created by a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(KeyEvent keyEvent) {
					
		//Lets the shown rectangles of this container note the given key event that has been created by a key typing.
		getRefShownRectangles().forEach(r -> r.noteKeyTyping(keyEvent));
	}
	
	//method
	/**
	 * Paints this container using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected void paintContent(BRS rectangleStructure, Graphics graphics) {
		
		graphics.translate(-getContentXPosition(), -getContentYPosition());
		
		//Paints the rectangles of this container that are shown.
		getRefShownRectangles().forEach(r -> r.paintUsingRelativePosition(graphics));
		
		graphics.translate(getContentXPosition(), getContentYPosition());
	}
	
	//method
	/**
	 * Sets the dialog this rectangle belongs to.
	 * 
	 * @param dialog
	 * @throws Exception if this rectangle or a rectangle of this container already belongs to an other dialog
	 */
	protected void setDialog(Dialog<?> dialog) {
		
		//Calls method of the base class.
		super.setDialog(dialog);
		
		//Sets the dialog of the rectangles of this container.
		getRefRectangles().forEach(r -> r.setDialog(dialog));
	}
	
	//method
	/**
	 * Sets the relative mouse position of this container.
	 * 
	 * @param relativeMouseXPosition
	 * @parma mouseYPosition
	 */
	protected void setRelativeMousePosition(int relativeMouseXPosition, int relativeMouseYPosition) {
		
		//Calls method of the base class.
		super.setRelativeMousePosition(relativeMouseXPosition, relativeMouseYPosition);
		
		//Sets the relative mouse position of the rectangles of this container that are shown.
		getRefShownRectangles().forEach(r -> r.setRelativeMousePosition(getMouseXPosition(), getMouseYPosition()));
	}
}
