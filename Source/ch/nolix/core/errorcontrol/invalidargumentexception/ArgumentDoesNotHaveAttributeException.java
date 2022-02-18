//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentDoesNotHaveAttributeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an object does undesirably not have a specific attribute.
 * 
 * A {@link ArgumentDoesNotHaveAttributeException} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2019-01-31
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotHaveAttributeException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param attributeType
	 * @return a safe attribute type for the given argument type.
	 * @throws IllegalArgumentException if the given attribute type is null.
	 */
	private static String createSafeAttributeType(final Class<?> attributeType) {
		
		//Asserts that the given attribute type is not null.
		if (attributeType == null) {
			throw new IllegalArgumentException("The given attribute type is null.");
		}
		
		return attributeType.getSimpleName();
	}
	
	//static method
	/**
	 * @param attributeName
	 * @return a safe attribute name for the given argument ma,e.
	 * @throws IllegalArgumentException if the given attribute name is null.
	 * @throws IllegalArgumentException if the given attribute name is empty.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	private static String createSafeAttributeName(final String attributeName) {
		
		//Asserts that the given attribute name is not null.
		if (attributeName == null) {
			throw new IllegalArgumentException("The given attribute name is null.");
		}
		
		//Asserts that the given attribute name is not empty.
		if (attributeName.isEmpty()) {
			throw new IllegalArgumentException("The given attribute name is empty.");
		}
		
		//Asserts that the given attribute name is not blank.
		if (attributeName.isBlank()) {
			throw new IllegalArgumentException("The given attribute name is blank.");
		}
		
		return attributeName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException}
	 * for the given argument that does not have the attribute of the given attribute type.
	 * 
	 * @param argument
	 * @param attributeType
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public ArgumentDoesNotHaveAttributeException(final Object argument, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + createSafeAttributeType(attributeType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException}
	 * for the given argument that does not have the desired attribute that has the given attribute name.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws IllegalArgumentException if the given object is null.
	 * @throws IllegalArgumentException if the given attribute name is empty.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	public ArgumentDoesNotHaveAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + createSafeAttributeName(attributeName));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException}
	 * for the given argument that
	 * has the given argumentName and does not have the desired attribute that has the given attribute name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param attributeName
	 * @throws IllegalArgumentException if the given object is null.
	 * @throws IllegalArgumentException if the given attribute name is empty.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	public ArgumentDoesNotHaveAttributeException(
		final String argumentName,
		final Object argument,
		final String attributeName
	) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "does not have a " + createSafeAttributeName(attributeName));
	}
}
