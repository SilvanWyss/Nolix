//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnequalArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does undesirably not equal a certain .
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
@SuppressWarnings("serial")
public final class UnequalArgumentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param argument
	 * @param value
	 * @param <A> is the type of the given argument.
	 * @return a new {@link UnequalArgumentException} for the given argument and value.
	 */
	public static <A> UnequalArgumentException forArgumentAndValue(final A argument, final A value) {
		return new UnequalArgumentException(argument, value);
	}
	
	//static method
	/**
	 * @param argumentName
	 * @param argument
	 * @param value
	 * @return a new {@link UnequalArgumentException} for the given argumentName, argument and value.
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	public static UnequalArgumentException forArgumentNameAndArgumentAndValue(
		final String argumentName,
		final double argument,
		final double value
	) {
		return new UnequalArgumentException(argumentName, argument, value);
	}
	
	//static method
	/**
	 * @param argumentName
	 * @param argument
	 * @param value
	 * @return a new {@link UnequalArgumentException} for the given argumentName, argument and value.
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	public static UnequalArgumentException forArgumentNameAndArgumentAndValue(
		final String argumentName,
		final long argument,
		final long value
	) {
		return new UnequalArgumentException(argumentName, argument, value);
	}
	
	//constructor
	/**
	 * Creates a new {@link UnequalArgumentException} for the given argument and value.
	 * 
	 * @param argument
	 * @param value
	 * @param <A> is the type of the given argument.
	 */
	private <A> UnequalArgumentException(final A argument, final A value) {
		
		//Calls constructor of the base class.
		super(argument, "does not equal the " + value.getClass().getSimpleName() + " '" + value + "'");
	}
	
	//constructor
	/**
	 * Creates a new {@link UnequalArgumentException} for the given argumentName, argument and value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param value
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	private UnequalArgumentException(final String argumentName, final double argument, final double value) {
		
		//Calls constructor of the base class.
		super(argumentName,	argument, "does not equal " + value);
	}
	
	//constructor
	/**
	 * Creates a new {@link UnequalArgumentException} for the given argumentName, argument and value.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param value
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 */
	private UnequalArgumentException(final String argumentName, final long argument, final long value) {
		
		//Calls constructor of the base class.
		super(argumentName,	argument, "does not equal " + value);
	}
}
