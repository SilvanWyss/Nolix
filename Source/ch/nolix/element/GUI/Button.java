//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.element.color.Color;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class Button extends TextLineWidget<Button> {
	
	//constant
	public static final String TYPE_NAME = "Button";
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	
	//constructor
	/**
	 * Creates a new {@link Button}.
	 */
	public Button() {	
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new {@link Button} with the given text.
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
	 * @return true if the current {@link Button} has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Button}.
	 */
	public Button resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		getRefBaseLook().setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		return this;
	}
}
