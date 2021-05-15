//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link FrozenArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument is undesirably frozen.
 * 
 * @author Silvan Wyss
 * @date 2019-05-05
 * @lines 30
 */
@SuppressWarnings("serial")
public final class FrozenArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is frozen";
	
	//constructor
	/**
	 * Creates a new {@link FrozenArgumentException} for the given argument.
	 * 
	 * @param argument
	 */
	public FrozenArgumentException(final Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
}
