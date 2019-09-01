//package declaration
package ch.nolix.element.core;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public class PositiveInteger extends Integer {
	
	//constant
	public static final String TYPE_NAME = "PositiveInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 1;
	
	//static method
	/**
	 * @param specification
	 * @return a new positive integer from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static PositiveInteger fromSpecification(
		final BaseNode specification
	) {
		return new PositiveInteger(
			specification.getOneAttributeAsInt()
		);
	}
	
	//constructor
	/**
	 * Creates a new positive integer with a default value.
	 */
	public PositiveInteger() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new positive integer with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException
	 * if the given value is not positive.
	 */
	public PositiveInteger(final int value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is positive.
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isPositive();
	}
}
