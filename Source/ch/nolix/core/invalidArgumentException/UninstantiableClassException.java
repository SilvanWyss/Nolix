//package declaration
package ch.nolix.core.invalidArgumentException;

//class
/**
* A {@link UninstantiableClassException} is a {@link InvalidArgumentException}
* that is supposed to be thrown when a given class was undesirably tried to be instantiated.
* 
* @author Silvan Wyss
* @month 2019-03
* @lines 40
*/
@SuppressWarnings("serial")
public final class UninstantiableClassException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param _class
	 * @return a safe class for the given class.
	 * @throws RuntimeException if the given class is null.
	 */
	private static Class<?> createSafeClass(final Class<?> _class) {
		
		//Checks if the given class is not null.
		if (_class == null) {
			throw new RuntimeException("The given class is null.");
		}
		
		return _class;
	}
	
	//constructor
	/**
	 * Creates a new {@link UninstantiableClassException} for the given class.
	 * 
	 * @param _class
	 * @throws RuntimeException if the given class is null.
	 */
	public UninstantiableClassException(final Class<?> _class) {
		
		//Calls constructor of the base class.
		super(createSafeClass(_class));
	}
}
