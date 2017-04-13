//package declaration
package ch.nolix.common.exception;

//class
/**
 * An untimely method exception is an argument exception that is intended to be thrown when an object is not in a state for a certain method.
 * 
 * @author Silvan Wyss
 * @month 2017-04
 * @lines 30
 */
@SuppressWarnings("serial")
public final class UntimelyMethodException extends InvalidArgumentException {

	//constructor
	/**
	 * Creates new untimely method exception for the given argument that is not in a state for a method with the given method name.
	 * 
	 * @param argument
	 * @param methodName
	 * @throws RuntimeException if the given method name is null.
	 * @throws RuntimeException if the given method name is empty.
	 */
	public UntimelyMethodException(final Object argument, final String methodName) {
		
		//Calls constructor of the base class.
		super(new Argument(argument), new ErrorPredicate("is not in a state for " + methodName));
		
		//Checks if the given method name is not null.
		if (methodName == null) {
			throw new RuntimeException("The given method name is null.");
		}
		
		//Checks if the given method name is not empty.
		if (methodName.isEmpty()) {
			throw new RuntimeException("The given method name is empty.");
		}
	}
}
