//package declaration
package ch.nolix.element.dialog;

//class
/**
 * A button is a text line rectangle that is supposed to be used for left mouse button presses.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Button extends TextLineWidget<Button> {
		
	//constant
	public static final String SIMPLE_CLASS_NAME = "Button";
	
	//constructor
	/**
	 * Creates new button with default values.
	 */
	public Button() {	
		resetConfiguration();
	}
	
	//constructor
	/**
	 * Creates new button with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is null.
	 */
	public Button(final String text) {
		
		//Calls other constructor.
		this();
		
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
