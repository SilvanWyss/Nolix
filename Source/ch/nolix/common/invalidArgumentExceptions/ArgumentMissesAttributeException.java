//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
/**
 * A {@link ArgumentMissesAttributeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an object does undesirably not have a specific attribute.
 * 
 * A {@link ArgumentMissesAttributeException} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 90
 */
@SuppressWarnings("serial")
public final class ArgumentMissesAttributeException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param attributeType
	 * @return a safe attribute type for the given argument type.
	 * @throws RuntimeException if the given attribute type is null.
	 */
	private static String createSafeAttributeType(final Class<?> attributeType) {
		
		//Checks if the given attribute type is not null.
		if (attributeType == null) {
			throw new RuntimeException("The given attribute type is null.");
		}
		
		return attributeType.getSimpleName();
	}
	
	//static method
	/**
	 * @param attributeName
	 * @return a safe attribute name for the given argument ma,e.
	 * @throws RuntimeException if the given attribute name is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 * @throws RuntimeException if the given attribute name is blank.
	 */
	private static String createSafeAttribtueName(final String attributeName) {
		
		//Checks if the given attribute name is not null.
		if (attributeName == null) {
			throw new RuntimeException("The givne attribtue name is null.");
		}
		
		//Checks if the given attribute name is not empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The givne attribtue name is empty.");
		}
		
		//Checks if the given attribute name is not blank.
		if (attributeName.isBlank()) {
			throw new RuntimeException("The givne attribtue name is blank.");
		}
		
		return attributeName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentMissesAttributeException}
	 * for the given argument that does not have the attribute of the given attribute type.
	 * 
	 * @param argument
	 * @param attributeType
	 * @throws NullArgumentException if the given argument is null.
	 */
	public ArgumentMissesAttributeException(final Object argument, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + createSafeAttributeType(attributeType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentMissesAttributeException}
	 * for the given argument that does not have the desired attribute that has the given attribute name.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws RuntimeException if the given object is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 * @throws RuntimeException if the given attribute name is blank.
	 */
	public ArgumentMissesAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(argument, "does not have a " + createSafeAttribtueName(attributeName));
	}
}
