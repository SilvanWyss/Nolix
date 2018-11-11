//package declaration
package ch.nolix.core.invalidArgumentException;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ArgumentName;
import ch.nolix.core.argument.ErrorPredicate;

//class
/**
 * An out of range exception is an invalid argument exception
 * that is supposed to be thrown when a value is undesired not in a given range.
 * 
 * An out of range exception is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 110
 */
@SuppressWarnings("serial")
public final class OutOfRangeArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new out of range argument exception
	 * for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public OutOfRangeArgumentException(
		final double argument,
		final double min,
		final double max
	) {		
		//Calls constructor of the base class.
		super(
			new Argument(argument),
			new ErrorPredicate("is not in [" + min + ", " + max + "]")
		);
	}
	
	//constructor
	/**
	 * Creates a new out of range argument exception
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
	public OutOfRangeArgumentException(
		final String argumentName,
		final double argument,
		final double min,
		final double max
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("is not in [" + min + ", " + max + "]")
		);
	}
	
	//constructor
	/**
	 * Creates a new out of range argument exception
	 * for the given argument and for the range defined by the given min and max.
	 * 
	 * @param argument
	 * @param min
	 * @param max
	 */
	public OutOfRangeArgumentException(
		final long argument,
		final long min,
		final long max
	) {		
		//Calls constructor of the base class.
		super(
			new Argument(argument),
			new ErrorPredicate("is not in [" + min + ", " + max + "]")
		);
	}
	
	//constructor
	/**
	 * Creates a new out of range argument exception
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
			new ErrorPredicate("is not in [" + min + ", " + max + "]")
		);
	}
}
