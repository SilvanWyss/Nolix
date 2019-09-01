//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
/**
 * A positive argument exception is an argument exception
 * that is supposed to be thrown when an argument is undesired positive.
 * 
 * A positive argument exception is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
@SuppressWarnings("serial")
public final class PositiveArgumentException extends InvalidArgumentException {

	//constant
	private static final String ERROR_PREDICATE = "is positive";
	
	//constructor
	/**
	 * Creates a new positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public PositiveArgumentException(final double argument) {

		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new positive argument exception for the given argument.
	 * 
	 * @param argument
	 */
	public PositiveArgumentException(final long argument) {

		//Calls constructor of the base class.
		super(argument,	ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new positive argument exception
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public PositiveArgumentException(
		final String argumentName,
		final double argument
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			ERROR_PREDICATE
		);
	}
	
	//constructor
	/**
	 * Creates a new positive argument exception
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public PositiveArgumentException(
		final String argumentName,
		final long argument
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
