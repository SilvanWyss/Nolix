//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
* A {@link BiggerArgumentException} is a {@link InvalidArgumentException}
* that is supposed to be thrown when an argument is undesirably bigger than a given limit.
* 
* @author Silvan Wyss
* @month 2016-02
* @lines 80
*/
@SuppressWarnings("serial")
public final class BiggerArgumentException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates a new bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public BiggerArgumentException(final double argument, final double limit) {
		
		//Calls constructor of the base class.
		super(argument, "is bigger than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new bigger argument exception for the given argument and limit.
	 * 
	 * @param argument
	 * @param limit
	 */
	public BiggerArgumentException(final long argument, final long limit) {
		
		//Calls constructor of the base class.
		super(argument, "is bigger than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new bigger argument exception for the given argument,
	 * that has the given argument name, and the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public BiggerArgumentException(
		final String argumentName,
		final double argument,
		final double limit) {
		
		//Calls constructor of the base class.
		super(argumentName,	argument, "is bigger than " + limit);
	}
	
	//constructor
	/**
	 * Creates a new bigger argument exception for the given argument,
	 * that has the given argument name, and the given limit.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param limit
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public BiggerArgumentException(
		final String argumentName,
		final long argument,
		final long limit) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "is bigger than " + limit);
	}
}
