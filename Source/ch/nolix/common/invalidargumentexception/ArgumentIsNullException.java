//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsNullException} is a {@link InvalidArgumentException}
 * that is intended to be thrown when an argument is undesired null.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 70
 */
@SuppressWarnings("serial")
public final class ArgumentIsNullException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "is null";
	
	//static method
	/**
	 * @return a safe argument name for the given argument type.
	 * @throws IllegalArgumentException if the given argument type is null.
	 */
	private static String createSafeArgumentName(final Class<?> argumentType) {
		
		//Asserts that the given argument type is not null.
		if (argumentType == null) {
			throw new IllegalArgumentException("The given argument type is null.");
		}
		
		return argumentType.getSimpleName();
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentIsNullException}.
	 */
	public ArgumentIsNullException() {
		
		//Calls constructor of the base class.
		super(null, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentIsNullException} for an argument of the given argument type.
	 * 
	 * @param argumentType
	 * @throws IllegalArgumentException if the given argument type is null.
	 */
	public ArgumentIsNullException(final Class<?> argumentType) {
		
		//Calls constructor of the base class.
		super(createSafeArgumentName(argumentType), null, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new null argument name exception for an argument with the given argument name.
	 * 
	 * @param argumentName
	 * @throws IllegalArgumentException if the given argument name is null.
	 * @throws IllegalArgumentException if the given argument name is empty.
	 */
	public ArgumentIsNullException(final String argumentName) {
		
		//Calls constructor of the base class.
		super(argumentName, null, ERROR_PREDICATE);
	}
}
