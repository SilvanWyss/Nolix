/*
 * file:	Integer.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.core;

//class
public final class Integer extends Integeroid {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Integer";
	
	//default value
	public static final int DEFAULT_VALUE = 0;
	
	//constructor
	/**
	 * Creates new integer with default attributes.
	 */
	public Integer() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
		
	//constructor
	/**
	 * Creates new integer with the given value.
	 * 
	 * @param value
	 */
	public Integer(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
		
	//method
	/**
	 * Resets this integer.
	 */
	public final void reset() {
		setValue(DEFAULT_VALUE);
	}
}
