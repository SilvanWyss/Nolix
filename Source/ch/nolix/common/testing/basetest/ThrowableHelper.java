//package declaration
package ch.nolix.common.testing.basetest;

//class
final class ThrowableHelper {
	
	//constant
	public static final String DEFAULT_ERROR_MESSAGE = "An error occured.";
	
	//static method
	public static String getMessageFromThrowableOrDefaultErrorMessage(final Throwable throwable) {
		
		if (throwable == null) {
			return DEFAULT_ERROR_MESSAGE;
		}
		
		final var message = throwable.getMessage();
		
		if (message == null || message.isBlank()) {
			return DEFAULT_ERROR_MESSAGE;
		}
		
		return message;
	}
	
	//constructor
	private ThrowableHelper() {}
}
