/*
 * file:	Button.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	60
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.specification.Specification;

//class
/**
 * A button is a borderable rectangle with a text that is supposed for left mouse button presses.
 * 
 * @author Silvan Wyss
 */
public final class Button extends TextLineRectangle<Button> {
		
	//constant
	public final static String SIMPLE_CLASS_NAME = "Button";
	
	//constructor
	/**
	 * Creates new button with default values.
	 */
	public Button() {	
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new button with the given attributes.
	 * 
	 * @param attributes		The attributes of this button.
	 * @throws Exception if the given attributes are not valid
	 */
	public Button(final Iterable<Specification> attributes) {		
		resetConfiguration();	
		addOrChangeAttributes(attributes);
	}
	
	//constructor
	/**
	 * Creates new button with the given text.
	 * 
	 * @param text		The text of this button.
	 * @throws Exception if the given text is null
	 */
	public Button(final String text) {
		resetConfiguration();
		setText(text);
	}
	
	//method
	/**
	 * @param role		The role this button is requested to have.
	 * @return true if this button has the given role
	 */
	public boolean hasRole(final String role) {
		return false;
	}
}
