//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A non-positive argument exception is an argument exception
 * that is supposed to be thrown when a value is undesired not positive.
 * 
 * A non-positive argument exception is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 80
 */
@SuppressWarnings("serial")
public final class NonPositiveArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is not positive";
	
	//constructor
	/**
	 * Creates a new non-positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new non-positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate(ERROR_PREDICATE));
	}
	
	//constructor
	/**
	 * Creates a new non-positive argument exception
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final double argument
	) {		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
	
	//constructor
	/**
	 * Creates a new non-positive argument exception
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final long argument
	) {		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate(ERROR_PREDICATE)
		);
	}
}
