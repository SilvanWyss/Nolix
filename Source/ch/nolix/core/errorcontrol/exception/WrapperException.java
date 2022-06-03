//package declaration
package ch.nolix.core.errorcontrol.exception;

//class
@SuppressWarnings("serial")
public final class WrapperException extends RuntimeException {
	
	//constant
	public static final String DEFAULT_ERROR_MESSAGE = "An error occured.";
	
	//constructor
	public WrapperException(final Throwable error) {
		this(DEFAULT_ERROR_MESSAGE, error);
	}
	
	//constructor
	public WrapperException(final String errorMessage, final Throwable error) {
		super(errorMessage, error);
	}
}
