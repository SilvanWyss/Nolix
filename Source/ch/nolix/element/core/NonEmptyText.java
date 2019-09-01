//package declaration
package ch.nolix.element.core;

import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;

//class
/**
 * A non-empty text is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public class NonEmptyText extends Text {

	//type name
	public static final String TYPE_NAME = "NonEmptyText";
	
	//default value
	public static final String DEFAULT_VALUE = StringCatalogue.DEFAULT_STRING;
	
	//static method
	/**
	 * @param specification
	 * @return a new non-empty text from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static NonEmptyText fromSpecification(
		final BaseNode specification
	) {
		return new NonEmptyText(specification.getOneAttributeAsString());
	}
	
	//constructor
	/**
	 * Creates a new non empty text with a default value.
	 */
	public NonEmptyText() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new non empty text with the given value.
	 * 
	 * @param value
	 * @throws ArgumentIsNullException if the given value is null.
	 * @throws EmptyArgumentException if the given value is empty.
	 */
	public NonEmptyText(final String value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is not empty.
		Validator.suppose(value).thatIsNamed("value").isNotEmpty();
	}
}
