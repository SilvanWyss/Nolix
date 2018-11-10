//package declaration
package ch.nolix.core.invalidStateException;

/**
 * A {@link MissingParentException} is a {@link InvalidStateException}
 * that is supposed to be thrown when a {@link Object} does undesired not belong to a parent.
 * 
 * A {@link MissingParentException} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 90
 */
@SuppressWarnings("serial")
public final class MissingParentException extends InvalidStateException {
	
	//constant
	private static final String ERROR_PREDICATE = "does not belong to a parent";
	
	//static method
	/**
	 * @return the parent type name of the given parent type.
	 * @throws RuntimeException if the given parent type is null.
	 */
	private static String getParentTypeName(final Class<?> parentType) {
		
		//Checks if the given parent type is not null.
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
		
		//Checks if the given parent type is not null.
		if (parentType == null) {
			throw new RuntimeException("The given parent type is null.");
		}
		
		//Checks if the given parent type is not blank.
		if (parentType.isBlank()) {
			throw new RuntimeException("The given parent type is blank.");
		}
		
		return parentType;
	}
	
	//constructor
	/**
	 * Creates a new {@link MissingParentException} for the given object.
	 * 
	 * @param object
	 * @throws RuntimeException if the given object is null.
	 */
	public MissingParentException(final Object object) {
		
		//Calls constructor of the base class.
		super(object, ERROR_PREDICATE);
	}
	
	//constructor
	/**
	 * Creates a new {@link MissingParentException} for the given object and parent type.
	 * 
	 * @param object
	 * @param parent type
	 * @throws RuntimeException if the given object is null.
	 * @throws RuntimeException if the given parent type is null.
	 */
	public MissingParentException(final Object object, final Class<?> parentType) {	
		
		//Calls constructor of the base class.
		super(object, "does not belong to a " + getParentTypeName(parentType));
	}
	
	//constructor
	/**
	 * Creates a new {@link MissingParentException} for the given object and parent type.
	 * 
	 * @param object
	 * @param parent type
	 * @throws RuntimeException if the given object is null.
	 * @throws RuntimeException if the given parent type is null.
	 * @throws RuntimeException if the given parent type is blank.
	 */
	public MissingParentException(final Object object, final String parentType) {	
		
		//Calls constructor of the base class.
		super(object, "does not belong to a " + getParentTypeName(parentType));
	}
}
