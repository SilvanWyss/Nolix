//package declaration
package ch.nolix.element.GUI;

//class
/**
 * A button is a text line widget
 * that is supposed to be used for left mouse button presses.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
public final class Button extends TextLineWidget<Button> {
		
	//type name
	public static final String TYPE_NAME = "Button";
	
	//constructor
	/**
	 * Creates a new button with default values.
	 */
	public Button() {	
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new button with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is null.
	 */
	public Button(final String text) {
		
		//Calls other constructor.
		this();
		
		//Sets the text of this button.
		setText(text);
	}
	
	//method
	/**
	 * @param role
	 * @return true if this button has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
}
