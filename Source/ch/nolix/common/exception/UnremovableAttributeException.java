//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unremovable attribute exception is an attribute exception that is intended to be thrown when an object cannot remove an attribute that is desired to be removed.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 */
@SuppressWarnings("serial")
public final class UnremovableAttributeException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates new unremovable attribute exception for the given argument that cannot remove the attribute with the given attribute name.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given attribute name is null.
	 * @throws RuntimeException if the given attribute name is empty.
	 */
	public UnremovableAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("cannot remove " + attributeName));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		//Checks if the given attribute name is null.
		if (attributeName == null) {
			throw new RuntimeException("The given attribtue name is null.");
		}
		
		//Checks if the given attribute name is empty.
		if (attributeName.isEmpty()) {
			throw new RuntimeException("The given attribtue name is empty.");
		}
	}
}
