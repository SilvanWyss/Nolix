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
	 * @param class_
	 * @return a safe class for the given class.
	 * @throws RuntimeException if the given class is null.
	 */
	private static Class<?> createSafeClass(final Class<?> class_) {
		
		//Checks if the given class is not null.
		if (class_ == null) {
			throw new RuntimeException("The given class is null.");
		}
		
		return class_;
	}
	
	//constructor
	/**
	 * Creates a new {@link UninstantiableClassException} for the given class.
	 * 
	 * @param class_
	 * @throws RuntimeException if the given class is null.
	 */
	public UninstantiableClassException(final Class<?> class_) {
		
		//Calls constructor of the base class.
		super(createSafeClass(class_));
	}
}
