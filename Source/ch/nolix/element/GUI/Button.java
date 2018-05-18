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
	
	//constructor
	/**
	 * Creates a new {@link Button}.
	 */
	public Button() {
		reset();
		approveProperties();
		applyUsableConfiguration();
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
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		
		setMinWidth(100);
		setContentPosition(ContentPosition.Center);
		
		getRefBaseLook()
		.setBackgroundColor(Color.LIGHT_GREY)
		.setLeftPadding(10)
		.setRightPadding(10);
		
		getRefHoverLook()
		.setBackgroundColor(Color.GREY);
	}
}
