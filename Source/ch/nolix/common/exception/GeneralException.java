//own imports
package ch.nolix.common.exception;

//class
@SuppressWarnings("serial")
public final class GeneralException extends RuntimeException {
	
	//constant
	public static final String DEFAULT_ERROR_MESSAGE = "An error occured.";
	
	//static method
	private static String createSafeErroMessageFor(final String errorMessage) {
		
		if (errorMessage == null) {
			throw new IllegalArgumentException("The given error message is null.");
		}
		
		if (errorMessage.isBlank()) {
			throw new GeneralException("The given error message is blank.");
		}
		
		return errorMessage;
	}
	
	//constructor
	public GeneralException() {
		this(DEFAULT_ERROR_MESSAGE);
	}
	
	//constructor
	public GeneralException(final String errorMessage) {
		super(createSafeErroMessageFor(errorMessage));
	}
}
