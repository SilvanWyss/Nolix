//package declaration
package ch.nolix.element.core;

//own import
import ch.nolix.core.constants.StringCatalogue;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class Text extends Textoid {
	
	//type name
	public static final String TYPE_NAME = "Text";
	
	//default value
	public static final String DEFAULT_VALUE = StringCatalogue.EMPTY_STRING;
	
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
