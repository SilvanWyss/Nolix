/*
 * file:	ValueNotOneException.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	50
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A value not value exception is an exception that is intended to be thrown when a value is undesired not 1.
 */
@SuppressWarnings("serial")
public final class NonOneArgumentException extends RuntimeException {

	//constructor
	/**
	 * Creates new value not one exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonOneArgumentException(String name, double value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not positive.");
	}
	
	//constructor
	/**
	 * Creates new value not one exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonOneArgumentException(String name, int value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not positive.");
	}
	
	//constructor
	/**
	 * Creates new value not one exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonOneArgumentException(String name, long value) {
		
		//Calls constructor of the base class.
		super("The " + name + " " + value + " is not positive.");
	}
}
