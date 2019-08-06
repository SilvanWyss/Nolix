//package declaration
package ch.nolix.core.invalidArgumentExceptions;

//class
/**
 * A {@link NullArgumentException} is a {@link InvalidArgumentException}
 * that is intended to be thrown when an argument is undesired null.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 70
 */
@SuppressWarnings("serial")
public final class NullArgumentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is null";
	
	//static method
	/**
	 * @return a safe argument name for the given argument type.
	 * @throws RuntimeException if the given argument type is null.
	 */
	private static String createSafeArgumentName(final Class<?> argumentType) {
		
		//Checks if the given argument type is not null.
		if (argumentType == null) {
			throw new RuntimeException("The given argument type is null.");
		}
		
		return argumentType.getSimpleName();
	}
	
	//constructor
	/**
	 * Creates a new {@link NullArgumentException}.
	 */
	public NullArgumentException() {
		
		//Calls constructor of the base class.
		super(null, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link NullArgumentException} for an argument of the given argument type.
	 * 
	 * @param argumentType
	 * @throws RuntimeException if the given argument type is null.
	 */
	public NullArgumentException(final Class<?> argumentType) {
		
		//Calls constructor of the base class.
		super(createSafeArgumentName(argumentType), null, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new null argument name exception for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws RuntimeException if the given argument name is null.
	 * @throws RuntimeException if the given argument name is empty.
	 */
	public NullArgumentException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, null, ERROR_PREDICATE);
	}
}
