//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
 * A smaller argument exception is an exception that is intended to be thrown when an argument is undesired smaller than a given limit.
 *
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 80
 */
@SuppressWarnings("serial")
public final class SmallerArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new smaller argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public SmallerArgumentException(final double argument, final double limit) {
		
		//Calls constructor of the base class.
		super(
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new smaller argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public SmallerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new smaller argument exception for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final double argument,
		final double limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new smaller argument exception for the given argument,
	 * that has the given argument name, and for the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public SmallerArgumentException(
		final String argumentName,
		final long argument,
		final long limit
	) {
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is smaller than " + limit
		);
	}
}
