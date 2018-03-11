//package declaration
package ch.nolix.element.core;

import ch.nolix.primitive.invalidArgumentException.NegativeArgumentException;

//class
/**
 * A non-negative integer is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public class NonNegativeInteger extends Integer {
	
	//type name
	public static final String TYPE_NAME = "NonNegativeInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 0;
	
	//constructor
	/**
	 * Creates a new non-negative integer with default attributes.
	 */
	public NonNegativeInteger() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new non-negative integer with the given value.
	 * 
	 * @param value
	 * @throws NegativeArgumentException if the given value is negative.
	 */
	public NonNegativeInteger(int value) {
		
		//Calls constructor of the base class.
		super(value);
		
		//Checks if the given value is not negative.
		if (value < 0) {
			throw new NegativeArgumentException(value);
		}
	}
}
