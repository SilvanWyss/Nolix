/*
 * file:	NullArgumentException.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	30
 */

//package declaration
package ch.nolix.common.exception;

//class
/**
* A null argument exception is an exception that is intended to be thrown when an argument is undesired null.
*/
@SuppressWarnings("serial")
public final class NullArgumentException extends ArgumentException {

	//constant
	private final static String PREDICATE = "is null";
	
	public NullArgumentException() {
		super(DEFAULT_ARGUMENT_NAME, PREDICATE);
	}
	
	//constructor
	/**
	 * Creates new null argument name exception with the given argument name.
	 * 
	 * @param argumentName	The name of the argument of this null argument exception
	 */
	public NullArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, PREDICATE);
	}
}
