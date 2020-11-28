//package declaration
package ch.nolix.common.wrapperexception;

//class
@SuppressWarnings("serial")
public final class RunException extends RuntimeException {
	
	//static method
	private static String createSafeErrorMessage(final String actionName) {
		
		if (actionName == null) {
			throw new NullPointerException("The given action name is null.");
		}
		
		if (actionName.isBlank()) {
			throw new IllegalArgumentException("The given action name is blank.");
		}
		
		return ("An error occured by " + actionName + ".");
	}
	
	//constructor
	public RunException(final String actionName) {
		super(createSafeErrorMessage(actionName));
	}
}
