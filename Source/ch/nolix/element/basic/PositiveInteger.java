/*
 * file:	PositiveInteger.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	60
 */

//package declaration
package ch.nolix.element.basic;

//own imports
import ch.nolix.core.validator.Validator;

//class
public class PositiveInteger extends Integeroid {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "PositiveInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 1;
	
	//constructor
	/**
	 * Creates new positive integer with default attributes.
	 */
	public PositiveInteger() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new positive integer with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positive
	 */
	public PositiveInteger(int value) {
		super(value);
	}
	
	//method
	/**
	 * Resets this positive integer.
	 */
	public final void reset() {
		setValue(DEFAULT_VALUE);
	}
	
	//method
	/**
	 * Sets the value of this positive integer.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positive
	 */
	public final void setValue(int value) {
		
		Validator.throwExceptionIfValueIsNotPositive("value", value);
		
		//Calls method of the base class.
		super.setValue(value);
	}
}
