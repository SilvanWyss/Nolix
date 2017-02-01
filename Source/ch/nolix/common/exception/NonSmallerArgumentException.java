//package declaration
package ch.nolix.common.exception;

@SuppressWarnings("serial")
/**
 * A non smaller argument exception is an argument exception that is intended to be thrown when an argument is undesired not smaller than a given limit.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 30
 */
public final class NonSmallerArgumentException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates new non smaller argument exception for the givne argument and limit
	 * 
	 * @param argument
	 * @param limi
	 */
	public NonSmallerArgumentException(final double argument, final double  limit) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("is not smaller than " + limit));
	}

	//constructor
	/**
	 * Creates new non smaller argument exception for the givne argument and limit
	 * 
	 * @param argument
	 * @param limi
	 */
	public NonSmallerArgumentException(final long argument, final long  limit) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("is not smaller than " + limit));
	}
}
