//package declaration
package ch.nolix.element.core;

import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;

//class
/**
 * A positive floating point number
 * is a floating point number that is positive.
 * 
 * A positive floating point number is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 40
 */
public class PositiveFloatingPointNumber extends FloatingPointNumber {
		
	//default value
	public static final double DEFAULT_VALUE = 1.0;

	//static method
	/**
	 * @param specification
	 * @return a new positive floating point number from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static PositiveFloatingPointNumber fromSpecification(final BaseNode specification) {
		return new PositiveFloatingPointNumber(specification.getOneAttributeAsDouble());
	}
	
	//constructor
	/**
	 * Creates a new positive floating point number with a default value.
	 */
	public PositiveFloatingPointNumber() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}

	//constructor
	/**
	 * Creates a new positive floating point number with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException
	 * if the given value is not positive.
	 */
	public PositiveFloatingPointNumber(final double value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is positive.
		Validator.suppose(value).thatIsNamed("value").isPositive();
	}
}
