//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;

//class
/**
 * A {@link NonPositiveArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably not positive.
 * 
 * A {@link NonPositiveArgumentException} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 140
 */
@SuppressWarnings("serial")
public final class NonPositiveArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is not positive";
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final BigDecimal argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final BigInteger argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final double argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public NonPositiveArgumentException(final long argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final BigDecimal argument
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final BigInteger argument
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final double argument
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonPositiveArgumentException}
	 * for the given argument that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public NonPositiveArgumentException(
		final String argumentName,
		final long argument
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, ERROR_PREDICATE);
	}
}
