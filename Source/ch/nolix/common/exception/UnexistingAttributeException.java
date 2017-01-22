//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unexisting attribute exception is an argument exception that is intended to be thrown when an argument does not have a desired attribute.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 60
 */
@SuppressWarnings("serial")
public final class UnexistingAttributeException extends ArgumentException {
	
	//constructor
	/**
	 * Creates new unexisting attribute exception for the given argument that is of the given argument type.
	 * 
	 * @param argument
	 * @param argumentType
	 * @throws RuntimeException if the given argument is null.
	 */
	public UnexistingAttributeException(final Object argument, final Class<?> argumentType) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("has no " + argumentType.getSimpleName()));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
	}
	
	//constructor
	/**
	 * Creates new unexisting attribute exception for the given argument and attribute name.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given attribute name is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 */
	public UnexistingAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("has no " + attributeName));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		//Checks if the given attribute name is null.
		if (attributeName == null) {
			throw new RuntimeException("The given attribtue name is null.");
		}
		
		//Checks if the given attribute name is empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
