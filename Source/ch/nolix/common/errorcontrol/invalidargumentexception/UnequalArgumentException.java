//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnequalArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument does undesirably not equal an expected value.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 90
 */
@SuppressWarnings("serial")
public final class UnequalArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new {@link UnequalArgumentException} for the given argument and the given expectedValue.
	 * 
	 * @param argument
	 * @param expectedValue
	 * @param <A> is the type of the given argument.
	 */
	public <A> UnequalArgumentException(final A argument, final A expectedValue) {
		
		//Calls constructor of the base class.
		super(argument, "does not equal " + createValidStringRepresentationInProbableQuotes(expectedValue));
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument and the given expected value.
	 * 
	 * @param argument
	 * @param expectedValue
	 */
	public UnequalArgumentException(final double argument, final double expectedValue) {
		
		//Calls constructor of the base class.
		super(argument, "does not equal " + expectedValue);
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
		super(argument, "does not equal " + expectedValue);
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument,
	 * that has the given argument name, and for the given expected value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param expectedValue
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public UnequalArgumentException(
		final String argumentName,
		final double argument,
		final double expectedValue
	) {
		//Calls constructor of the base class.
		super(argumentName,	argument, "does not equal " + expectedValue);
	}
	
	//constructor
	/**
	 * Creates a new unequal argument exception for the given argument,
	 * that has the given argument name, and for the given expected value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param expectedValue
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public UnequalArgumentException(
		final String argumentName,
		final long argument,
		final long expectedValue
	) {
		//Calls constructor of the base class.
		super(argumentName,	argument, "does not equal " + expectedValue);
	}
}
