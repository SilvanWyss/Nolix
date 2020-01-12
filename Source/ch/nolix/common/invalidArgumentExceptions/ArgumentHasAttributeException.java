//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
@SuppressWarnings("serial")
public final class ArgumentHasAttributeException extends InvalidArgumentException {
	
	//static method
	private static String createSafeAttribtueName(final String attributeName) {
		
		//Checks if the given attributeName is not null.
		if (attributeName == null) {
			throw new RuntimeException("The givne attribute name is null.");
		}
		
		//Checks if the given attributeName is not blank.
		if (attributeName.isBlank()) {
			throw new RuntimeException("The givne attribute name is blank.");
		}
		
		return attributeName;
	}
	
	//static method
	private static String createSafeAttributeType(final Class<?> attributeType) {
		
		//Checks if the given attribute type is not null.
		if (attributeType == null) {
			throw new RuntimeException("The given attribute type is null.");
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
		super(argument, "has a " + createSafeAttribtueName(attributeName));
	}
}
