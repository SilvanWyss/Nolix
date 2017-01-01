/*
 * file:	NonBiggerArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	60
 */

//package declaration
package ch.nolix.common.exception;

/**
 * A non bigger argument exception is an argument exception that is indeed to be thrown when an argument is undesired not (!) bigger than a given value.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public class NonBiggerArgumentException extends ArgumentException {

	//constructor
	/**
	 * Creates new non bigger argument exception with the given argument and value.
	 * 
	 * @param argument		The argument of this non bigger argument exception.
	 * @param value			The value the argument of this non bigger argument exception is not bigger than.
	 */
	public NonBiggerArgumentException(
		final double argument,
		final double value
	) {
		
		//Calls constructor of the base class.
		super(argument, " is not bigger than " + value);
	}

	//constructor
	/**
	 * Creates new non bigger argument exception with the given argument name, argument and value.
	 * 
	 * @param argumentName		The name of the argument of this non bigger argument exception.
	 * @param argument			The argument of this non bigger argument exception.
	 * @param value				The value the argument of this non bigger argument exception is not bigger than.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final double argument,
		final double value
	) {
		
		super(argumentName, argument, " is not bigger than " + value);
	}
	
	//constructor
	/**
	 * Creates new non bigger argument exception with the given argument name, argument and value.
	 * 
	 * @param argumentName		The name of the argument of this non bigger argument exception.
	 * @param argument			The argument of this non bigger argument exception.
	 * @param value				The value the argument of this non bigger argument exception is not bigger than.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final long argument,
		final long value
	) {
		
		super(argumentName, argument, " is not bigger than " + value);
	}
}
