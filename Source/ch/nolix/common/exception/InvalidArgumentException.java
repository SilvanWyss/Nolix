/*
 * file:	InvalidValueException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	40
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An invalid value exception is an exception that is intended to be thrown when a value is not valid.
 */
@SuppressWarnings("serial")
public final class InvalidArgumentException extends RuntimeException {
	
	//constructor
	/**
	 * Creates new invalid value exception for the given value.
	 * 
	 * @param value
	 */
	public InvalidArgumentException(Object value) {
		
		//Calls constructor of the base class.
		super("The " + value.getClass().getSimpleName() + " '" + value.toString() + "' is not valid.");
	}

	//constructor
	/**
	 * Creates new invalid value exception for the given value, that has the given name.
	 * 
	 * @param name
	 * @param value
	 */
	public InvalidArgumentException(String name, Object value) {
		
		//Calls constructor of the base class.
		super("The given " + name + " '" + value.toString() + "' is not valid.");
	}
}
