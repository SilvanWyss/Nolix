//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
* A {@link UninstantiableArgumentException} is a {@link InvalidArgumentException}
* that is supposed to be thrown when a given class was undesirably tried to be instantiated.
* 
* @author Silvan Wyss
* @month 2019-03
* @lines 40
*/
@SuppressWarnings("serial")
public final class UninstantiableArgumentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param _class
	 * @return a safe class for the given class.
	 * @throws IllegalArgumentException if the given class is null.
	 */
	private static Class<?> createSafeClass(final Class<?> _class) {
		
		//Asserts that the given class is not null.
		if (_class == null) {
			throw new IllegalArgumentException("The given class is null.");
		}
		
		return _class;
	}
	
	//constructor
	/**
	 * Creates a new {@link UninstantiableArgumentException} for the given class.
	 * 
	 * @param _class
	 * @throws IllegalArgumentException if the given class is null.
	 */
	public UninstantiableArgumentException(final Class<?> _class) {
		
		//Calls constructor of the base class.
		super(createSafeClass(_class));
	}
}
