//package declaration
package ch.nolix.common.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentBelongsToParentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument belongs undesirably to a parent.
 * 
 * @author Silvan Wyss
 * @date 2019-10-01
 * @lines 40
 */
@SuppressWarnings("serial")
public class ArgumentBelongsToParentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param parent
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
	 * Creates a new {@link ArgumentBelongsToParentException} for the given argument that belongs to the given parent.
	 * 
	 * @param argument
	 * @param parent
	 * @throws IllegalArgumentException if the given parent is null.
	 */
	public ArgumentBelongsToParentException(final Object argument, final Object parent) {
		
		//Calls constructor of the base class.
		super(argument, "belongs to a " + createSafeTypeName(parent));
	}
}
