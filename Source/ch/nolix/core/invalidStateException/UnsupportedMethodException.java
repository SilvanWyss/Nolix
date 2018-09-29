//package declaration
package ch.nolix.core.invalidStateException;

//class
/**
 * An unsupported method exception is an invalid state exception that is intended to be thrown
 * when an object does not support a called method.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 40
 */
@SuppressWarnings("serial")
public final class UnsupportedMethodException extends InvalidStateException {
	
	//constructor
	/**
	 * Creates a new unsupported method exception
	 * for the given object that does not support the method with the given method name.
	 * 
	 * @param object
	 * @param methodName
	 * @throws RuntimeException if the given argument is not an instance.
	 * @throws RuntimeException if the given method name is not an instance.
	 * @throws RuntimeException if the given method name is empty.
	 */
	public UnsupportedMethodException(final Object object, final String methodName) {
		
		//Calls constructor of the base class.
		super(object, "does not support the method " + methodName);
		
		//Checks if the given method name is an instance.
		if (methodName == null) {
			throw new RuntimeException("The given method name is not an instance.");
		}
		
		//Checks if the given attribute name is not empty.
		if (methodName.isEmpty()) {
			throw new RuntimeException("The given method name is empty.");
		}
	}
}
