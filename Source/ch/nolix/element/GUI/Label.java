/*
 * file:	Label.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	180
 */

//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator.Validator;

//class
/**
 * A label is a text line rectangle whose text cannot be changed by the user.
 * 
 * @author Silvan Wyss
 */
public final class Label extends TextLineWidget<Label> {
	
	//type name
	public static final String TYPE_NAME = "Label";
	
	//attribute header
	private static final String ROLE_HEADER = "Role";
	
	//optional attribute
	private LabelRole role;
	
	//constructor
	/**
	 * Creates new label with default values.
	 */
	public Label() {	
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new label with the given text.
	 * 
	 * @param text
	 * @throws Exception if the given text is null
	 */
	public Label(final String text) {
		resetConfiguration();
		setText(text);
	}
	
	//constructor
	/**
	 * Creates new label with the given attributes.
	 * 
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public Label(final Iterable<StandardSpecification> attributes) {
		resetConfiguration();
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Adds or change the given attribute to this label.
	 * 
	 * @param attribute		The attribute to add or change to this label.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ROLE_HEADER:
				setRole(LabelRole.valueOf(attribute.getOneAttributeToString()));
				break;	
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this label
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (hasRole()) {
			attributes.addAtEnd(new StandardSpecification(ROLE_HEADER, role.toString()));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of this label
	 * @throws UnexistingAttributeExcetpion if this label has no role
	 */
	public LabelRole getRole() {
		
		//Checks if this label has a role.
		if (!hasRole()) {
			throw new UnexistingAttributeException(this, ROLE_HEADER);
		}
		
		return role;
	}
	
	//method
	/**
	 * @return true if this label has a role
	 */
	public boolean hasRole() {
		return (role != null);
	}
	
	//method
	/**
	 * @param role		The role this label is requested to have.
	 * @return true of this label has the given role
	 */
	public boolean hasRole(final String role) {
		
		if (!hasRole()) {
			return false;
		}
		
		return this.role.toString().equals(role);
	}
	
	//method
	/**
	 * Lets this label note a pressed key.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets this label note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	/**
	 * Lets this label note a typed key.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Sets the role of this label.
	 * 
	 * @param role		The role that is set to this label.
	 * @return this label
	 * @throws Exception if the given role is null
	 */
	public final Label setRole(final LabelRole role) {
		
		//Checks the given role.
		Validator.throwExceptionIfValueIsNull("role", role);

		this.role = role;
		
		return this;
	}	
}
