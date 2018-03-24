/*
 * file:	Container.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	110
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.validator.Validator;

//class
/**
 * A container is a widget that can contain other widgets.
 * 
 * @param <C> The type of a container.
 * @param <BWS> The type of the widget structures of a container.
 */
public abstract class Container<
	C extends Container<C, BWS>,
	BWS extends BorderWidgetStructure<BWS>
>
extends BorderWidget<C, BWS> {
	
	//attribute
	private ContainerRole role;
	
	public List<StandardSpecification> getAttributes() {
		
		List<StandardSpecification> attributes = super.getAttributes();
		
		if (hasRole()) {
			attributes.addAtEnd(new StandardSpecification("Role", role.toString()));
		}
		
		return attributes;
	}
	
	//abstract method
	/**
	 * @return the widgets of this container that are shown.
	 */
	public abstract ReadContainer<Widget<?, ?>> getRefShownWidgets();
	
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
		getRefWidgets().forEach(r -> r.resetConfiguration());
	}
	
	public void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.getHeader()) {
		case "Role":
			setRole(ContainerRole.valueOf(attribute.getOneAttributeAsString()));
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
	 * Paints this container using the given rectangle structure and painter.
	 * 
	 * @param rectangleStructure
	 * @param painter
	 */
	protected void paintContent(
		final BWS rectangleStructure,
		final IPainter painter
	) {
		//Paints the rectangles of this container that are shown.
		getRefShownWidgets().forEach(r -> r.paintUsingPositionOnContainer(painter));
	}
	
	//method
	/**
	 * Sets the dialog this rectangle belongs to.
	 * 
	 * @param dialog
	 * @throws Exception if this rectangle or a rectangle of this container already belongs to an other dialog
	 */
	protected void setGUI(GUI<?> dialog) {
		
		//Calls method of the base class.
		super.setGUI(dialog);
		
		//Sets the dialog of the rectangles of this container.
		getRefWidgets().forEach(r -> r.setGUI(dialog));
	}
	
	//method
	/**
	 * Sets the relative mouse position of this container.
	 * 
	 * @param relativeMouseXPosition
	 * @parma mouseYPosition
	 */
	public void setCursorPositionFromParentContainer(int relativeMouseXPosition, int relativeMouseYPosition) {
		
		//Calls method of the base class.
		super.setCursorPositionFromParentContainer(relativeMouseXPosition, relativeMouseYPosition);
		
		//Sets the relative mouse position of the rectangles of this container that are shown.
		getRefShownWidgets().forEach(r -> r.setCursorPositionFromParentContainer(getMouseXPosition(), getMouseYPosition()));
	}
}
