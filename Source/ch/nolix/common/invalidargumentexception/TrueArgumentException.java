//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A true argument exception is an invalid argument exception
 * that is supposed to be thrown when an argument is undesired true.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
@SuppressWarnings("serial")
public final class TrueArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is true";
	
	//constructor
	/**
	 * Creates a new true argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public TrueArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//TODO: Turn around arguments.
	//constructor
	/**
	 * Creates a new true argument exception
	 * for an argument that has the given argument name.
	 * 
	 * @param argument
	 * @param argumentName
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public TrueArgumentException(final Object argument, final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
