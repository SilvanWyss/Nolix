//package declaration
package ch.nolix.element.core;

//own import
import ch.nolix.core.constants.StringCatalogue;

//class
/**
 * A text is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public class Text extends Textoid {
	
	//default value
	public static final String DEFAULT_VALUE = StringCatalogue.EMPTY_STRING;
	
	//type name
	public static final String TYPE_NAME = "Text";	
	
	//constructor
	/**
	 * Creates a new text with a default value.
	 */
	public Text() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
		
	//constructor
	/**
	 * Creates a new text with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 */
	public Text(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
