//package declaration
package ch.nolix.common.invalidStateException;

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
	 * Creates new unremovable attribute exception
	 * for the given object that cannot remove the attribute with the given attribute name.
	 * 
	 * @param object
	 * @param attributeName
	 * @throws RuntimeException if the given attribute name is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 */
	public UnremovableAttributeException(final Object object, final String attributeName) {
		
		//Calls constructor of the base class.
		super(object, "cannot remove " + attributeName);
		
		//Checks if the given attribute name is not null.
		if (attributeName == null) {
			throw new RuntimeException("The given attribtue name is null.");
		}
		
		//Checks if the given attribute name is not empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
