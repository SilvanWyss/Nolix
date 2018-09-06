//package declaration
package ch.nolix.primitive.invalidStateException;

//class
/**
 * An unremovable attribute exception is an invalid state exception that is intended to be thrown
 * when an object cannot remove an undesired attribute.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 */
@SuppressWarnings("serial")
public final class UnremovableAttributeException extends InvalidStateException {

	//constructor
	/**
	 * Creates a new unremovable attribute exception
	 * for the given object that cannot remove the attribute with the given attribute name.
	 * 
	 * @param object
	 * @param attributeName
	 * @throws RuntimeException if the given attribute name is not an instance.
	 * @throws RuntimeException if the given attribute name is empty.
	 */
	public UnremovableAttributeException(final Object object, final String attributeName) {
		
		//Calls constructor of the base class.
		super(object, "cannot remove " + attributeName);
		
		//Checks if the given attribute name is an instance.
		if (attributeName == null) {
			throw new RuntimeException("The given attribtue name is not an instance.");
		}
		
		//Checks if the given attribute name is not empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
