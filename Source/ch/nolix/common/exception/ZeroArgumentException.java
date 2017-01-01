/*
 * file:	ZeroArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	80
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * A zero argument exception is an exception that is intended to be thrown when am argument is undesired zero.
 */
@SuppressWarnings("serial")
public final class ZeroArgumentException extends ArgumentException {
	
	//constant
	private final static String PREDICATE = "is 0";
	
	//constructor
	/**
	 * Creates new zero argument exception with the given argument.
	 * 
	 * @param argument		The argument of the zero argument exception.
	 */
	public ZeroArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new zero argument exception with the given argument.
	 * 
	 * @param argument		The argument of the zero argument exception.
	 */
	public ZeroArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new zero argument exception with the given argument name.
	 * 
	 * @param argumentName		The name of the argument of the zero argument exception.
	 */
	public ZeroArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new zero argument exception with the given argument name and argument.
	 * 
	 * @param argumentName		The name of the argument of the zero argument exception.
	 * @param argument			The argument of the zero argument exception.
	 */
	public ZeroArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}	
	
	//constructor
	/**
	 * Creates new zero argument exception with the given argument name and argument.
	 * 
	 * @param argumentName		The name of the argument of the zero argument exception.
	 * @param argument			The argument of the zero argument exception.
	 */
	public ZeroArgumentException(final String argumentName, final long argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, PREDICATE);
	}
}
