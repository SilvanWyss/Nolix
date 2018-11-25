//package declaration
package ch.nolix.core.invalidArgumentException;

@SuppressWarnings("serial")
/**
 * A non smaller argument exception is an argument exception
 * that is intended to be thrown when an argument is undesired not smaller than a given limit.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 60
 */
public final class NonSmallerArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates a new non smaller argument exception for the given argument and limit
	 * 
	 * @param argument
	 * @param limi
	 */
	public NonSmallerArgumentException(final double argument, final double  limit) {
		
		//Calls constructor of the base class.
		super(
			argument,
			"is not smaller than " + limit
		);
	}

	//constructor
	/**
	 * Creates a new non smaller argument exception for the given argument and limit
	 * 
	 * @param argument
	 * @param limit
	 */
	public NonSmallerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(
			argument,
			"is not smaller than " + limit
		);
	}
	
	//constructor
	/**
	 * Creates a new non smaller argument exception for the given argument
	 * that has the given argument name and that is not smaller than the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NonSmallerArgumentException(
		final String argumentName,
		final double argument,
		final double limit
	) {
		
		//Calls constructor of the base class.
		super(
			argumentName,
			argument,
			"is not smaller than " + limit
		);
	}
}
