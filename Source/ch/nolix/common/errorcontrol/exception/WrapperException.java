//package declaration
package ch.nolix.common.errorcontrol.exception;

//class
@SuppressWarnings("serial")
public final class WrapperException extends RuntimeException {
	
	//constructor
	public WrapperException(final Exception exception) {
		super("An error occured.", exception);
	}
}
