//package declaration
package ch.nolix.common.exception;

//class
/**
 * A non bigger argument exception is an argument exception that is intended to be thrown when an argument is undesired not bigger than a given limit.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 */
@SuppressWarnings("serial")
public class NonBiggerArgumentException extends ArgumentException {

	//constructor
	/**
	 * Creates new non bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public NonBiggerArgumentException(final double argument, final double limit) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("is not bigger than " + limit));
	}
	
	//constructor
	/**
	 * Creates new non bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public NonBiggerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("is not bigger than " + limit));
	}

	//constructor
	/**
	 * Creates new non bigger argument exception for the given argument, that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final double argument,
		final double limit
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("is not bigger than " + limit)
		);
	}
	
	//constructor
	/**
	 * Creates new non bigger argument exception for the given argument, that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonBiggerArgumentException(
		final String argumentName,
		final long argument,
		final long limit
	) {
		//Calls constructor of the base class.
		super(
			new ArgumentName(argumentName),
			new Argument(argument),
			new ErrorPredicate("is not bigger than " + limit)
		);
	}
}
