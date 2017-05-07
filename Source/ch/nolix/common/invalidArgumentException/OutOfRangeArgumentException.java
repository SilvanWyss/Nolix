//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
 * An out of range exception is an invalid argument exception that is intended to be thrown when a value is undesired not in a given range.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 50
 */
@SuppressWarnings("serial")
public final class OutOfRangeArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates new out of range argument exception for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public OutOfRangeArgumentException(final long argument, final long min, final long max) {
		
		//Calls constructor of the base class.
		super(
			new Argument(argument),
			new ErrorPredicate("is not between [" + min + ", " + max + "]")
		);
	}
	
	//constructor
	/**
	 * Creates new out of range argument exception for the given argument, that has the given argument name, and for the ragne defined by the given min and max.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param min
	 * @param max
	 */
	public OutOfRangeArgumentException(
		final String argumentName,
		final long argument,
		final long min,
		final long max
	) {
		
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("is not between [" + min + ", " + max + "]")
		);
	}
}
