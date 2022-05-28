//package declaration
package ch.nolix.core.testing.validation;

//class
@SuppressWarnings("serial")
public final class ExpectationException extends RuntimeException {
	
	//static method
	public static ExpectationException withErrorMessage(final String errorMessage) {
		return new ExpectationException(errorMessage);
	}
	
	//constructor
	private ExpectationException(final String errorMessage) {
		super(errorMessage);
	}
}
