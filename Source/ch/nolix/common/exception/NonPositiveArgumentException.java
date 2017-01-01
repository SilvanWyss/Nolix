/*
 * file:	NoPositiveValueException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	50
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A no positive value exception is an exception that is intended to be thrown when a value is undesired not positive.
 */
@SuppressWarnings("serial")
public final class NonPositiveArgumentException extends ArgumentException {

	//constant
	private final static String PREDICATE = "is not positive";
	
	public NonPositiveArgumentException(final double argument) {
		
		super(argument, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new no positive value exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonPositiveArgumentException(String name, double value) {
		super(name, value, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new no positive value exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonPositiveArgumentException(String name, int value) {
		
		//Calls constructor of the base class.
		super(name, value, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new no positive value exception for the given value, that has the given value name.
	 * 
	 * @param name
	 * @param value
	 */
	public NonPositiveArgumentException(String name, long value) {
		
		//Calls constructor of the base class.
		super(name, value, PREDICATE);
	}
}
