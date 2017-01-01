/*
 * file:	NonNegativeArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A non negative argument exception is an exception that is intended to be thrown when an argument is undesired not negative.
 */
@SuppressWarnings("serial")
public final class NonNegativeArgumentException extends ArgumentException {

	//constant
	private final static String PREDICATE = "is negative";
	
	//constructor
	public NonNegativeArgumentException(double argument) {
		super(argument, PREDICATE);
	}
	
	//constructor
	public NonNegativeArgumentException(long argument) {
		super(argument, PREDICATE);
	}
	
	//constructor
	public NonNegativeArgumentException(final String argumentName, final int argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}
	
	//constructor
	public NonNegativeArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}
}
