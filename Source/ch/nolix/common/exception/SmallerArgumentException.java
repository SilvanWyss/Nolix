/*
 * file:	ValueUnderMinException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	110
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An argument under min exception is an exception that is intended to be thrown when an argument is undesired smaller than a given minimum.
 */
@SuppressWarnings("serial")
public final class SmallerArgumentException extends ArgumentException {
	
	//constructor
	/**
	 * Creates new under min argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument	The argument of the under min argument exception.
	 * @param min		The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final double argument,
		final double min) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, min);
	}
	
	//constructor
	/**
	 * Creates new under min argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument	The argument of the under min argument exception.
	 * @param min		The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final int argument,
		final int min) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, min);
	}
	
	//constructor
	/**
	 * Creates new under min argument exception with the default argument name and the given argument and maximum.
	 * 
	 * @param argument	The argument of the under min argument exception.
	 * @param min		The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final long argument,
		final long min) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument, min);
	}
	
	//constructor
	/**
	 * Creates new under min argument exception with the given argument name, argument and maximum.
	 * 
	 * @param argumentName	The name of the argument of the under min argument exception.
	 * @param argument		The argument of the under min argument exception.
	 * @param min			The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final double argument,
		final double min) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is smaller than " + min);
	}
	
	//constructor
	/**
	 * Creates new under min argument exception with the given argument name, argument and maximum.
	 * 
	 * @param argumentName	The name of the argument of the under min argument exception.
	 * @param argument		The argument of the under min argument exception.
	 * @param min			The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final int argument,
		final int min) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is smaller than " + min);
	}
	
	//constructor
	/**
	 * Creates new under min argument exception with the given argument name, argument and maximum.
	 * 
	 * @param argumentName	The name of the argument of the under min argument exception.
	 * @param argument		The argument of the under min argument exception.
	 * @param min			The minimum of the under min argument exception.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final long argument,
		final long min) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, " is smaller than " + min);
	}
}
