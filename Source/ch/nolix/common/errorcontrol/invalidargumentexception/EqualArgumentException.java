//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * An equal argument exception is an argument exception
 * that is supposed to be thrown when an argument equals undesired a given unwanted value.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 120
 */
@SuppressWarnings("serial")
public final class EqualArgumentException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates a new equal argument exception for the given argument and unwanted value.
	 * 
	 * @param argument
	 * @param unwantedValue
	 */
	public EqualArgumentException(final double argument, final double unwantedValue) {
		
		//Calls constructor of the base class.
		super(argument, "equals" + unwantedValue);
	}
	
	//constructor
	/**
	 * Creates a new equal argument exception for the given argument and unwanted value.
	 * 
	 * @param argument
	 * @param unwantedValue
	 */
	public EqualArgumentException(final long argument, final long unwantedValue) {
		
		//Calls constructor of the base class.
		super(argument, "equals" + unwantedValue);
	}
	
	//constructor
	/**
	 * Creates a new equal argument exception for the given argument and unwanted value.
	 * 
	 * @param argument
	 * @param unwantedValue
	 */
	public EqualArgumentException(final Object argument, final Object unwantedValue) {
		
		//Calls constructor of the base class.
		super(argument, "equals " + unwantedValue);
	}
	
	//constructor
	/**
	 * Creates a new equal argument exception
	 * for the given argument, that has the given argument name, and for the given unwanted value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param unwantedValue
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public EqualArgumentException(
		final String argumentName,
		final double argument,
		final double unwantedValue
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, "equals" + unwantedValue);
	}
	
	//constructor
	/**
	 * Creates a new equal argument exception
	 * for the given argument, that has the given argument name, and for the given unwanted value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param unwantedValue
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public EqualArgumentException(
		final String argumentName,
		final long argument,
		final long unwantedValue
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, "equals" + unwantedValue);
	}
	
	//constructor
	/**
	 * Creates a new equal argument exception
	 * for the given argument, that has the given argument name, and for the given unwanted value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param unwantedValue
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public EqualArgumentException(
		final String argumentName,
		final Object argument,
		final Object unwantedValue
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, "equals" + unwantedValue);
	}
}
