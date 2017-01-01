/*
 * file:	ValueAboveMaxException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	110
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
* An argument above max exception is an exception that is intended to be thrown when an argument is undesired bigger than a given maximum.
*/
@SuppressWarnings("serial")
public final class BiggerArgumentException extends ArgumentException {

	//constructor
	/**
	 * Creates new above max argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final double argument,
		final double max) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, max);
	}
	
	//constructor
	/**
	 * Creates new above max argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final int argument,
		final int max) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, max);
	}
	
	//constructor
	/**
	 * Creates new above max argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final long argument,
		final long max) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, max);
	}
	
	//constructor
	/**
	 * Creates new above max argument exception with the given argument name, argument and maximumimum.
	 * 
	 * @param argumentName	The name of the argument of the above max argument exception.
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final String argumentName,
		final double argument,
		final double max) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is bigger than " + max);
	}
	
	//constructor
	/**
	 * Creates new above max argument exception with the given argument name, argument and maximum.
	 * 
	 * @param argumentName	The name of the argument of the above max argument exception.
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final String argumentName,
		final int argument,
		final int max) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is bigger than " + max);
	}
	
	//constructor
	/**
	 * Creates new above max argument exception with the given argument name, argument and maximum.
	 * 
	 * @param argumentName	The name of the argument of the above max argument exception.
	 * @param argument		The argument of the above max argument exception.
	 * @param max			The maximum of the above max argument exception.
	 */
	public BiggerArgumentException(
		final String argumentName,
		final long argument,
		final long max) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is bigger than " + max);
	}
}
