//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
* A {@link UninstantiableArgumentException} is a {@link InvalidArgumentException}
* that is supposed to be thrown when a given class was undesirably tried to be instantiated.
* 
* @author Silvan Wyss
* @date 2019-03-02
* @lines 40
*/
@SuppressWarnings("serial")
public final class UninstantiableArgumentException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param pClass
	 * @return a safe class for the given class.
	 * @throws IllegalArgumentException if the given pClass is null.
	 */
	private static Class<?> createSafeClass(final Class<?> pClass) {
		
		//Asserts that the given class is not null.
		if (pClass == null) {
			throw new IllegalArgumentException("The given class is null.");
		}
		
		return pClass;
	}
	
	//constructor
	/**
	 * Creates a new {@link UninstantiableArgumentException} for the given class.
	 * 
	 * @param pClass
	 * @throws IllegalArgumentException if the given pClass is null.
	 */
	public UninstantiableArgumentException(final Class<?> pClass) {
		
		//Calls constructor of the base class.
		super(createSafeClass(pClass));
	}
}
