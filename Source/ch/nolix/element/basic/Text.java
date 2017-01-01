//package declaration
package ch.nolix.element.basic;

//own import
import ch.nolix.common.constants.StringManager;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class Text extends Textoid {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Text";
	
	//default value
	public final static String DEFAULT_VALUE = StringManager.EMPTY_STRING;
	
	//constructor
	/**
	 * Creates new text with a default value.
	 */
	public Text() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
		
	//constructor
	/**
	 * Creates new text with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 */
	public Text(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}

	//method
	/**
	 * Resets this text.
	 */
	public final void reset() {
		setValue(DEFAULT_VALUE);
	}
}
