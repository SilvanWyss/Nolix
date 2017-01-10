//package declaration
package ch.nolix.common.exception;

//class
/**
 * A positive argument exception is an argument exception that is intended to be thrown when an argument is undesired positive.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
@SuppressWarnings("serial")
public final class PositiveArgumentException extends ArgumentException {

	//constant
	private final static String ERROR_PREDICATE = "is positive";
	
	//constructor
	/**
	 * Creates new positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public PositiveArgumentException(final double argument) {

		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public PositiveArgumentException(final long argument) {

		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new positive argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public PositiveArgumentException(final String argumentName, final double argument) {
		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
}
