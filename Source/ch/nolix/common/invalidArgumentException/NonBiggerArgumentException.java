//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
 * A {@link NonBiggerArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument is undesirably not bigger than a given limit.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
@SuppressWarnings("serial")
public class NonBiggerArgumentException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates a new non bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public NonBiggerArgumentException(final double argument, final double limit) {
		
		//Calls constructor of the base class.
		super(argument, "is not bigger than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new non bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public NonBiggerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(argument, "is not bigger than " + limit);
	}

	//constructor
	/**
	 * Creates a new {@link NonBiggerArgumentException}
	 * for the given argument, that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final double argument,
		final double limit
	) {
		//Calls constructor of the base class.
		super(argumentName,	argument, "is not bigger than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new {@link NonBiggerArgumentException}
	 * for the given argument, that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final long argument,
		final long limit
	) {
		//Calls constructor of the base class.
		super(argumentName, argument, "is not bigger than " + limit);
	}
}
