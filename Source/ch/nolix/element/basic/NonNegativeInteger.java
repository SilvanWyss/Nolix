/*
 * file:	NonNegativeInteger.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	60
 */

//package declaration
package ch.nolix.element.basic;

//own import
import ch.nolix.common.util.Validator;

//class
public class NonNegativeInteger extends Integeroid {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "NonNegativeInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 0;
	
	//constructor
	/**
	 * Creates new non-negative integer with default attributes.
	 */
	public NonNegativeInteger() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new non-negative integer with the given value.
	 * 
	 * @param value
	 */
	public NonNegativeInteger(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//method
	/**
	 * Resets this non-negative integer.
	 */
	public final void reset() {
		setValue(DEFAULT_VALUE);
	}
	
	//method
	/**
	 * Sets the value of this non-negative integer.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positive
	 */
	public final void setValue(int value) {

		Validator.throwExceptionIfValueIsNegative("value", value);
		
		//Calls method of the base class.
		super.setValue(value);
	}
}
