//package declaration
package ch.nolix.core.invalidStateException;

//class
/**
 * An unexisting attribute exception is an invalid state exception
 * that is intended to be thrown when an object has not an a desired attribute.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 50
 */
@SuppressWarnings("serial")
public final class UnexistingAttributeException extends InvalidStateException {
	
	//constructor
	/**
	 * Creates new unexisting attribute exception
	 * for the given object that has not the desired attribute of the given attribute type.
	 * 
	 * @param object
	 * @param argumentType
	 * @throws RuntimeException if the given argument is null.
	 */
	public UnexistingAttributeException(final Object object, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(object, "has no " + attributeType.getSimpleName());
	}
	
	//constructor
	/**
	 * Creates new unexisting attribute exception
	 * for the given object that has not the desired attribute with the given attribute name.
	 * 
	 * @param object
	 * @param attributeName
	 * @throws RuntimeException if the given attribute name is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 */
	public UnexistingAttributeException(final Object object, final String attributeName) {
		
		//Calls constructor of the base class.
		super(object, "has no " + attributeName);
		
		//Checks if the given attribute name is not null.
		if (attributeName == null) {
			throw new RuntimeException("The given argument name is null.");
		}
		
		//Checks if the given attribute name is not empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
