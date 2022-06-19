//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentDoesNotHaveAttributeException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when an object does undesirably not have a specific attribute.
 * 
 * @author Silvan Wyss
 * @date 2019-01-31
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotHaveAttributeException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param attributeType
	 * @return the name of the given attribtueType.
	 * @throws IllegalArgumentException if the given attribtueType is null.
	 */
	private static String getNameOfAttributeType(final Class<?> attributeType) {
		
		//Asserts that the given attribtueType is not null.
		if (attributeType == null) {
			throw new IllegalArgumentException("The given attribute type is null.");
		}
		
		return attributeType.getSimpleName();
	}
	
	//static method
	/**
	 * @param attributeName
	 * @return a valid attribute name of the given attributeName.
	 * @throws IllegalArgumentException if the given attributeName is null.
	 * @throws IllegalArgumentException if the given attributeName is blank.
	 */
	private static String getValidAttributeNameOfAttributeName(final String attributeName) {
		
		//Asserts that the given attributeName is not null.
		if (attributeName == null) {
			throw new IllegalArgumentException("The given attribute name is null.");
		}
		
		//Asserts that the given attributeName is not blank.
		if (attributeName.isBlank()) {
			throw new IllegalArgumentException("The given attribute name is blank.");
		}
		
		return attributeName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given argument and attributeType.
	 * 
	 * @param argument
	 * @param attributeType
	 * @throws IllegalArgumentException if the given attributeType is null.
	 */
	public ArgumentDoesNotHaveAttributeException(final Object argument, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + getNameOfAttributeType(attributeType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException} for the given argument and attributeName.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws IllegalArgumentException if the given attributeName is null.
	 * @throws IllegalArgumentException if the given attributeName is blank.
	 */
	public ArgumentDoesNotHaveAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + getValidAttributeNameOfAttributeName(attributeName));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException} for
	 * the given argumentName, argument and attributeType.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param attributeType
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 * @throws IllegalArgumentException if the given attributeType is null.
	 */
	public ArgumentDoesNotHaveAttributeException(
		final String argumentName,
		final Object argument,
		final Class<?> attributeType
	) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "does not have a " + getNameOfAttributeType(attributeType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotHaveAttributeException} for
	 * the given argumentName, argument and attributeName.
	 * 
	 * @param argumentName
	 * @param argument
	 * @param attributeName
	 * @throws IllegalArgumentException if the given argumentName is null.
	 * @throws IllegalArgumentException if the given argumentName is blank.
	 * @throws IllegalArgumentException if the given attributeName is null.
	 * @throws IllegalArgumentException if the given attributeName is blank.
	 */
	public ArgumentDoesNotHaveAttributeException(
		final String argumentName,
		final Object argument,
		final String attributeName
	) {
		
		//Calls constructor of the base class.
		super(argumentName, argument, "does not have a " + getValidAttributeNameOfAttributeName(attributeName));
	}
}
