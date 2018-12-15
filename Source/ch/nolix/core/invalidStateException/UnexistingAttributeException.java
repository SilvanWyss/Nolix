//package declaration
package ch.nolix.core.invalidStateException;

//own imports
import ch.nolix.core.validator2.Validator;

//class
/**
 * An unexisting attribute exception is an invalid state exception
 * that is supposed to be thrown when an object has not an a desired attribute.
 * 
 * An unexisting attribute exception is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-02
 * @lines 50
 */
@SuppressWarnings("serial")
public final class UnexistingAttributeException extends InvalidStateException {
	
	//constructor
	/**
	 * Creates a new unexisting attribute exception
	 * for the given object that has not the desired attribute of the given attribute type.
	 * 
	 * @param object
	 * @param attributeType
	 * @throws NullArgumentException if the given object is null.
	 */
	public UnexistingAttributeException(final Object object, final Class<?> attributeType) {
		
		//Calls constructor of the base class.
		super(object, "has no " + attributeType.getSimpleName());
	}
	
	//constructor
	/**
	 * Creates a new unexisting attribute exception
	 * for the given object that has not the desired attribute that has the given attribute name.
	 * 
	 * @param object
	 * @param attributeName
	 * @throws NullArgumentException if the given object is null.
	 * @throws NullArgumentException if the given attribute name is null.
	 * @throws EmptyArgumentException if the given attribute name is empty.
	 */
	public UnexistingAttributeException(final Object object, final String attributeName) {
		
		//Calls constructor of the base class.
		super(object, "has no " + attributeName);
		
		//Checks if the given attribute name is not null or empty.
		Validator.suppose(attributeName).isNotEmpty();
	}
}
