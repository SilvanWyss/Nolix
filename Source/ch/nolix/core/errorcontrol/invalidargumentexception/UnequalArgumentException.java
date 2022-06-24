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
	
	//constructor
	/**
	 * Creates a new {@link UnequalArgumentException} for the given argument and value.
	 * 
	 * @param argument
	 * @param value
	 * @param <A> is the type of the given argument.
	 */
	public <A> UnequalArgumentException(final A argument, final A value) {
		
		//Calls constructor of the base class.
		super(argument, "does not equal " + value);
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
	public UnequalArgumentException(final String argumentName, final double argument, final double value) {
		
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
	public UnequalArgumentException(final String argumentName, final long argument, final long value) {
		
		//Calls constructor of the base class.
		super(argumentName,	argument, "does not equal " + value);
	}
}
