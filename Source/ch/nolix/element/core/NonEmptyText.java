//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A non-empty text is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
public class NonEmptyText extends Textoid {

	//type name
	public static final String TYPE_NAME = "NonEmptyText";
	
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
		
		//Checks if the given value is not empty.
		Validator.suppose(value).thatIsNamed("value").isNotEmpty();
	}
}
