/*
 * file:	NegativeArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	50
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A negative argument exception is an exception that is intended to be thrown when an argument is undesired negative.
 */
@SuppressWarnings("serial")
public final class NegativeArgumentException extends ArgumentException {
	
	//constant
	private final static String PREDICATE = "is negative";
	
	public NegativeArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument, PREDICATE);
	}
	
	//constructor
	public NegativeArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}
}
