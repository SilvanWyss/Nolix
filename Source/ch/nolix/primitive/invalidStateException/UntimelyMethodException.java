//package declaration
package ch.nolix.primitive.invalidStateException;

//class
/**
 * An untimely method exception is an invalid state exception
 * that is intended to be thrown when an object is not in a state for a called method.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 40
 */
@SuppressWarnings("serial")
public final class UntimelyMethodException extends InvalidStateException {

	//constructor
	/**
	 * Creates a new untimely method exception
	 * for the given object that is not in a state for the called method with the given method name.
	 * 
	 * @param object
	 * @param methodName
	 * @throws RuntimeException if the given method name is not an instance.
	 * @throws RuntimeException if the given method name is empty.
	 */
	public UntimelyMethodException(final Object object, final String methodName) {
		
		//Calls constructor of the base class.
		super(object, "is not in a state for the method " + methodName);
		
		//Checks if the given method name is an instance.
		if (methodName == null) {
			throw new RuntimeException("The given method name is not an instance.");
		}
		
		//Checks if the given method name is not empty.
		if (methodName.isEmpty()) {
			throw new RuntimeException("The given method name is empty.");
		}
	}
}
