//package declaration
package ch.nolix.common.exception;

//class
/**
 * An unsupported method exception is an argument exception that is intended to be thrown when on the argument a method is called that is not supported.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 */
@SuppressWarnings("serial")
public final class UnsupportedMethodException extends ArgumentException {
	
	//constructor
	/**
	 * Creates new unsupported method exception for the given argument that does not support the method with the given method name.
	 * 
	 * @param argument
	 * @param methodName
	 * @throws RuntimeException if the given argument is null.
	 * @throws RuntimeException if the given method name is null.
	 * @throws RuntimeException if the given method name is empty.
	 */
	public UnsupportedMethodException(final Object argument, final String methodName) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("does not support the method " + methodName ));
		
		//Checks if the given argument is null.
		if (argument == null) {
			throw new RuntimeException("The given argument is null.");
		}
		
		//Checks if the given attribute name is null.
		if (methodName == null) {
			throw new RuntimeException("The given method name is null.");
		}
		
		//Checks if the given attribute name is empty.
		if (methodName.isEmpty()) {
			throw new RuntimeException("The given method name is empty.");
		}
	}
}
