//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A {@link ArgumentCannotRemoveAttributeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when an argument cannot remove a specific attribute.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 50
 */
@SuppressWarnings("serial")
public final class ArgumentCannotRemoveAttributeException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param attributeName
	 * @return a safe attribute name for the given argument name.
	 * @throws IllegalArgumentException if the given attribute name is null.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	private static String createSafeAttributeName(final String attributeName) {
		
		//Asserts that the given attribute name is not null.
		if (attributeName == null) {
			throw new IllegalArgumentException("The given attribute name is null.");
		}
		
		//Asserts that the given attribute name is not blank.
		if (attributeName.isBlank()) {
			throw new IllegalArgumentException("The given attribute name is blank.");
		}
		
		return attributeName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentCannotRemoveAttributeException}
	 * for the given argument, that cannot remove the attribute, that has the given attribute name.
	 * 
	 * @param argument
	 * @param attributeName
	 * @throws IllegalArgumentException if the given attribute name is null.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	public ArgumentCannotRemoveAttributeException(final Object argument, final String attributeName) {
		
		//Calls constructor of the base class.
		super(argument, "cannot remove " + createSafeAttributeName(attributeName));
	}
}
