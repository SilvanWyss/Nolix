//package declaration
package ch.nolix.common.exception;

//class
/**
 * A non positive argument exception is an argument exception that is intended to be thrown when a value is undesired not positive.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 70
 */
@SuppressWarnings("serial")
public final class NonPositiveArgumentException extends InvalidArgumentException {

	//constant
	private final static String ERROR_PREDICATE = "is not positive";
	
	//constructor
	/**
	 * Creates new non positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new non positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates new non positive argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonPositiveArgumentException(String argumentName, double argument) {
		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
	
	//constructor
	/**
	 * Creates new non positive argument exception for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonPositiveArgumentException(String argumentName, long argument) {
		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
}
