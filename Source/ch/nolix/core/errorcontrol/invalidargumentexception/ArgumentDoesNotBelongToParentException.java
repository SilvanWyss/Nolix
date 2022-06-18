//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentDoesNotBelongToParentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does undesirably not belong to a parent.
 * 
 * @author Silvan Wyss
 * @date 2022-01-30
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotBelongToParentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param parentType
	 * @return the name of the given parentType.
	 * @throws IllegalArgumentException if the given parentType is null.
	 */
	private static String getNameOfParentType(final Class<?> parentType) {
		
		//Asserts that the given parent type is not null.
		if (parentType == null) {
			throw new IllegalArgumentException("The given parent type is null.");
		}
		
		return parentType.getSimpleName();
	}
	
	//static method
	/**
	 * @param parentTypeName
	 * @return a valid parent type name from the given parentTypeName.
	 * @throws IllegalArgumentException if the given parentTypeName is null.
	 * @throws IllegalArgumentException if the given parentTypeName is blank.
	 */
	private static String getValidParentTypeNameFromParentTypeName(final String parentTypeName) {
		
		//Asserts that the given parentTypeName is not null.
		if (parentTypeName == null) {
			throw new IllegalArgumentException("The given parent type name is null.");
		}
		
		//Asserts that the given parentTypeName is not blank.
		if (parentTypeName.isBlank()) {
			throw new IllegalArgumentException("The given parent type name is blank.");
		}
		
		return parentTypeName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given argument.
	 * 
	 * @param argument
	 */
	public ArgumentDoesNotBelongToParentException(Object argument) {
		
		//Calls constructor of the base class.
		super(argument, "does not belong to a parent");
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given argument and parentType.
	 * 
	 * @param argument
	 * @param parentType
	 * @throws IllegalArgumentException if the given parentType is null.
	 */
	public ArgumentDoesNotBelongToParentException(final Object argument, final Class<?> parentType) {
		
		//Calls constructor of the base class.
		super(argument, "does not belong to a " + getNameOfParentType(parentType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given argument and parentTypeName.
	 * 
	 * @param argument
	 * @param parentTypeName
	 * @throws IllegalArgumentException if the given parentTypeName is null.
	 * @throws IllegalArgumentException if the given parentTypeName is blank.
	 */
	public ArgumentDoesNotBelongToParentException(final Object argument, final String parentTypeName) {
		
		//Calls constructor of the base class.
		super(argument, "does not belong to a " + getValidParentTypeNameFromParentTypeName(parentTypeName));
	}
}
