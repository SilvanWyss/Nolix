//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
/**
 * An in range argument exception is an invalid argument exception
 * that is supposed to be thrown when a value is undesired in a given range.
 * 
 * An in range argument exception is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 110
 */
@SuppressWarnings("serial")
public final class ArgumentIsInRangeException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new in range argument exception
	 * for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public ArgumentIsInRangeException(
		final double argument,
		final double min,
		final double max
	) {
		//Calls constructor of the base class.
		super(argument, "is in [" + min + ", " + max + "]");
	}
	
	//constructor
	/**
	 * Creates a new in range argument exception
	 * for the given argument, that has the given argument name,
	 * and for the range defined by the given min and max.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param min
	 * @param max
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
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
	 * Creates a new in range argument exception
	 * for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public ArgumentIsInRangeException(
		final long argument,
		final long min,
		final long max
	) {
		//Calls constructor of the base class.
		super(argument, "is in [" + min + ", " + max + "]");
	}
	
	//constructor
	/**
	 * Creates a new in range argument exception
	 * for the given argument, that has the given argument name,
	 * and for the range defined by the given min and max.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param min
	 * @param max
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
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
