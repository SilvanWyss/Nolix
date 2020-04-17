//package declaration
package ch.nolix.common.invalidArgumentExceptions;

//class
@SuppressWarnings("serial")
public final class ArgumentDoesNotBelongToParentException extends InvalidArgumentException {
	
	//constant
	private static final String ERROR_PREDICATE = "does not belong to a parent";
	
	//static method
	/**
	 * @return the parent type name of the given parent type.
	 * @throws RuntimeException if the given parent type is null.
	 */
	private static String getParentTypeName(final Class<?> parentType) {
		
		//Asserts that the given parent type is not null.
		if (parentType == null) {
			throw new RuntimeException("The given parent type is null.");
		}
		
		return parentType.getSimpleName();
	}
	
	//static method
	/**
	 * @return the parent type name of the given parent type.
	 * @throws RuntimeException if the given parent type is null.
	 * @throws RuntimeException if the given parent type is blank.
	 */
	private static String getParentTypeName(final String parentType) {
		
		//Asserts that the given parent type is not null.
		if (parentType == null) {
			throw new RuntimeException("The given parent type is null.");
		}
		
		//Asserts that the given parent type is not blank.
		if (parentType.isBlank()) {
			throw new RuntimeException("The given parent type is blank.");
		}
		
		return parentType;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given object.
	 * 
	 * @param object
	 */
	public ArgumentDoesNotBelongToParentException(Object argument) {
		
		//Calls constructor of the base class.
		super(argument, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given object and parent type.
	 * 
	 * @param object
	 * @param parent type
	 * @throws RuntimeException if the given parent type is null.
	 */
	public ArgumentDoesNotBelongToParentException(final Object object, final Class<?> parentType) {
		
		//Calls constructor of the base class.
		super(object, "does not belong to a " + getParentTypeName(parentType));
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotBelongToParentException} for the given object and parent type.
	 * 
	 * @param object
	 * @param parent type
	 * @throws RuntimeException if the given parent type is null.
	 * @throws RuntimeException if the given parent type is blank.
	 */
	public ArgumentDoesNotBelongToParentException(final Object object, final String parentType) {
		
		//Calls constructor of the base class.
		super(object, "does not belong to a " + getParentTypeName(parentType));
	}
}
