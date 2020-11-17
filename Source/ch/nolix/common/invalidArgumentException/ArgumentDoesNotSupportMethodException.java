//package declaration
package ch.nolix.common.invalidArgumentException;

//class
/**
* A {@link ArgumentDoesNotSupportMethodException} is a {@link InvalidArgumentException}
* that is supposed to be thrown when an argument does not support a called method.
* 
* A {@link ArgumentDoesNotSupportMethodException} is not mutable.
* 
* @author Silvan Wyss
* @month 2019-02
* @lines 50
*/
@SuppressWarnings("serial")
public final class ArgumentDoesNotSupportMethodException extends InvalidArgumentException {
	
	//static method
	/**
	 * @param methodName
	 * @return a safe method name for the given method name.
	 * @throws IllegalArgumentException if the given attribute name is null.
	 * @throws IllegalArgumentException if the given attribute name is blank.
	 */
	private static String createSafeMethodName(final String methodName) {
		
		//Asserts that the given method name is not null.
		if (methodName == null) {
			throw new IllegalArgumentException("The given method name is null.");
		}
		
		//Asserts that the given method name is not blank.
		if (methodName.isBlank()) {
			throw new IllegalArgumentException("The given method name is blank.");
		}
		
		return methodName;
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotSupportMethodException}
	 * for the given argument that does not support the method with the given method name.
	 * 
	 * @param argument
	 * @param methodName
	 * @throws IllegalArgumentException if the given argument is null.
	 * @throws IllegalArgumentException if the given method name is null.
	 * @throws IllegalArgumentException if the given method name is blank.
	 */
	public ArgumentDoesNotSupportMethodException(final Object argument, final String methodName) {
		
		//Calls constructor of the base class.
		super(argument, "does not support the method " + createSafeMethodName(methodName));
	}
}
