//package declaration
package ch.nolix.core.invalidArgumentException;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ArgumentName;
import ch.nolix.core.argument.ErrorPredicate;

//class
/**
 * An unequal argument exception is an argument exception that is intendend to be thrown when an argument does undesired not equal an expected value.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
@SuppressWarnings("serial")
public final class UnequalArgumentException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument and the given expected value.
	 * 
	 * @param argument
	 * @param expectedValue
	 */
	public UnequalArgumentException(final double argument, final double expectedValue) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("does not equal " + expectedValue));
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument and the given expected value.
	 * 
	 * @param argument
	 * @param expectedValue
	 */
	public UnequalArgumentException(final long argument, final long expectedValue) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("does not equal " + expectedValue));
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument, that has the given argument name, and for the given expected value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param expectedValue
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public UnequalArgumentException(
		final String argumentName,
		final double argument,
		final double expectedValue
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("does not equal " + expectedValue)
		);
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument, that has the given argument name, and for the given expected value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param expectedValue
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public UnequalArgumentException(
		final String argumentName,
		final long argument,
		final long expectedValue
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("does not equal " + expectedValue)
		);
	}
}
