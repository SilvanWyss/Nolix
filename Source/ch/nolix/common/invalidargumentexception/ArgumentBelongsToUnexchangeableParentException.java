//package declaration
package ch.nolix.common.invalidargumentexception;

//class
/**
 * A {@link ArgumentBelongsToUnexchangeableParentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument belongs undesirably to an unexchangeable parent.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 40
 */
@SuppressWarnings("serial")
public class ArgumentBelongsToUnexchangeableParentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @return a safe type name for the given parent.
	 * @throws IllegalArgumentException if the given parent is null.
	 */
	private static String createSafeTypeName(final Object parent) {
		
		//Asserts that the given parent is not null.
		if (parent == null) {
			throw new IllegalArgumentException("The given parent is null.");
		}
		
		return parent.getClass().getSimpleName();
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentBelongsToUnexchangeableParentException} for the given argument,
	 * that belongs to the given unexchangeable parent.
	 * 
	 * @param argument
	 * @param parent
	 * @throws IllegalArgumentException if the given parent is null.
	 */
	public ArgumentBelongsToUnexchangeableParentException(final Object argument, final Object parent) {
		
		//Calls constructor of the base class.
		super(argument, "belongs to a " + createSafeTypeName(parent));
	}
}
