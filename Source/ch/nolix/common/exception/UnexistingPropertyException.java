//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unexisting property exception is an argument exception that is intended to be thrown when an argument does not have a desired attribute.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 60
 */
@SuppressWarnings("serial")
public final class UnexistingPropertyException extends InvalidArgumentException {
	
	//constructor
	/**
	 * Creates new unexisting property exception for the given argument that is of the given argument type.
	 * 
	 * @param argument
	 * @param argumentType
	 * @throws RuntimeException if the given argument is null.
	 */
	public UnexistingPropertyException(final Object argument, final Class<?> argumentType) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("has no " + argumentType.getSimpleName()));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
	}
	
	//constructor
	/**
	 * Creates new unexisting property exception for the given argument and property name.
	 * 
	 * @param argument
	 * @param propertyName
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given property name is null.
	 * @throws RuntimeException if the given property name is empty.
	 */
	public UnexistingPropertyException(final Object argument, final String propertyName) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("has no " + propertyName));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		//Checks if the given property name is null.
		if (propertyName == null) {
			throw new RuntimeException("The given attribtue name is null.");
		}
		
		//Checks if the given property name is empty.
		if (propertyName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
