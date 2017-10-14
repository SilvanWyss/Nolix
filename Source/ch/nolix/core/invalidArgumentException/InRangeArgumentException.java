//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * An in range exception is an invalid argument exception
 * that is supposed to be thrown when a value is undesired in a given range.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
@SuppressWarnings("serial")
public final class InRangeArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates new in range argument exception
	 * for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public InRangeArgumentException(
		final long argument,
		final long min,
		final long max) {
		
		//Calls constructor of the base class.
		super(
			new Argument(argument),
			new ErrorPredicate("is in [" + min + ", " + max + "]")
		);
	}
	
	//constructor
	/**
	 * Creates new in range argument exception
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
	public InRangeArgumentException(
		final String argumentName,
		final long argument,
		final long min,
		final long max
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("is in [" + min + ", " + max + "]")
		);
	}
}
