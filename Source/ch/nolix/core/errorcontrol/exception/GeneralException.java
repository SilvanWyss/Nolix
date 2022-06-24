//own imports
package ch.nolix.core.errorcontrol.exception;

//class
@SuppressWarnings("serial")
public final class GeneralException extends RuntimeException {
	
	//static method
	public static GeneralException withErrorMessage(final String errorMessage) {
		return new GeneralException(errorMessage);
	}
	
	//static method
	private static String getValidErroMessageOfErrorMessage(final String errorMessage) {
		
		if (errorMessage == null) {
			throw new IllegalArgumentException("The given error message is null.");
		}
		
		if (errorMessage.isBlank()) {
			throw GeneralException.withErrorMessage("The given error message is blank.");
		}
		
		return errorMessage;
	}
	
	//constructor
	private GeneralException(final String errorMessage) {
		super(getValidErroMessageOfErrorMessage(errorMessage));
	}
}
