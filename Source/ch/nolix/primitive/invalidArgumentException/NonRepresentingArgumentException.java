//package declaration
package ch.nolix.primitive.invalidArgumentException;

//class
/**
 * A non representing argument exception is an exception that is intended to be thrown when an argument represents undesired no object of a given type.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 20
 */
@SuppressWarnings("serial")
public final class NonRepresentingArgumentException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates new non representing argument exception for the given argument and the given type.
	 * 
	 * @param argument
	 * @param type
	 */
	public NonRepresentingArgumentException(final Object argument, final Class<?> type) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("represents no " + type));
	}
}
