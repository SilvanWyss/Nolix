//package declaration
package ch.nolix.common.invalidargumentexception;

//class
@SuppressWarnings("serial")
public final class ArgumentHasAttributeException extends InvalidArgumentException {
	
	//static method
	private static String createSafeAttributeName(final String attributeName) {
		
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
	
	//static method
	private static String createSafeAttributeType(final Class<?> attributeType) {
		
		//Asserts that the given attribute type is not null.
		if (attributeType == null) {
			throw new IllegalArgumentException("The given attribute type is null.");
		}
		
		return attributeType.getSimpleName();
	}
	
	//constructor
	public ArgumentHasAttributeException(final Object argument, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(argument, "has a " + createSafeAttributeType(attributeType));
	}
	
	//constructor
	public ArgumentHasAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(argument, "has a " + createSafeAttributeName(attributeName));
	}
}
