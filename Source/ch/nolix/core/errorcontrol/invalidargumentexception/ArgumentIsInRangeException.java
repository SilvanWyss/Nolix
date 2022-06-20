//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsInRangeException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given value is undesirably in a certain range.
 * 
 * @author Silvan Wyss
 * @date 2017-10-14
 */
@SuppressWarnings("serial")
public final class ArgumentIsInRangeException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new {@link ArgumentIsInRangeException} for
	 * the given argument and range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public ArgumentIsInRangeException(final double argument, final double min, final double max) {
		
		//Calls constructor of the base class.
		super(argument, "is in [" + min + ", " + max + "]");
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentIsInRangeException} for
	 * the given argument and range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public ArgumentIsInRangeException(final long argument, final long min, final long max) {
		
		//Calls constructor of the base class.
		super(argument, "is in [" + min + ", " + max + "]");
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentIsInRangeException} for
	 * the given argumentName, argument and range defined by the given min and max.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param min
	 * @param max
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public ArgumentIsInRangeException(
		final String argumentName,
		final double argument,
		final double min,
		final double max
	) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "is in [" + min + ", " + max + "]");
	}

	//constructor
	/**
	 * Creates a new {@link ArgumentIsInRangeException} for
	 * the given argumentName, argument and range defined by the given min and max.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param min
	 * @param max
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is blank.
	 */
	public ArgumentIsInRangeException(
		final String argumentName,
		final long argument,
		final long min,
		final long max
	) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "is in [" + min + ", " + max + "]");
	}
}
