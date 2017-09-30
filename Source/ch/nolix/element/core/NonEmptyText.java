//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
public class NonEmptyText extends Textoid {

	//constant
	public static final String SIMPLE_CLASS_NAME = "NonEmptyText";
	
	//default value
	public static final String DEFAULT_VALUE = StringCatalogue.DEFAULT_STRING;
	
	//constructor
	/**
	 * Creates new non empty text with a default value.
	 */
	public NonEmptyText() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new non empty text with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is an empty string.
	 */
	public NonEmptyText(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//method
	/**
	 * Resets this non empty text.
	 */
	public final void reset() {
		setValue(DEFAULT_VALUE);
	}
	
	//method
	/**
	 * Sets the value of this non empty text.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is an empty string.
	 */
	public final void setValue(String value) {	
		
		//Checks if the given value is not null.
		Validator.suppose(value).isNotEmpty();
		
		//Calls method of the base class.
		super.setValue(value);
	}
}
