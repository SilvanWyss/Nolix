/*
 * file:	PositiveArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A positive argument exception is an argument exception that is indeed to be thrown when an argument is undesired positive.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class PositiveArgumentException extends ArgumentException {

	//constant
	private final static String PREDICATE = "is positive";
	
	//constructor
	/**
	 * Creates new positive argument exception with the given argument.
	 * 
	 * @param argument		The argument of this positive argument exception
	 */
	public PositiveArgumentException(final double argument) {

		//Calls constructor of the base class.
		super(argument, PREDICATE);
	}

	public PositiveArgumentException(String argumentName, long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}
}
