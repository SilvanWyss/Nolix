/*
 * file:	EmptyArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-02
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
 * An empty argument exception is an exception that is intended to be thrown when an argument is undesired empty.
 */
@SuppressWarnings("serial")
public final class EmptyArgumentException extends ArgumentException {

	//constant
	public final static String PREDICATE = "is empty";

	public EmptyArgumentException() {
		
		super(DEFAULT_ARGUMENT_NAME, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new empty argument exception with the given argument name.
	 * 
	 * @param argumentName	The name of the argument of the empty argument exception.
	 */
	public EmptyArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, PREDICATE);
	}
}
