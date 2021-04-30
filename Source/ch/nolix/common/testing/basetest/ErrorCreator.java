//package declaration
package ch.nolix.common.testing.basetest;

//Java import
import java.lang.reflect.InvocationTargetException;

//class
final class ErrorCreator {
	
	//static attributes
	private static final OccurancePlaceFinder occurancePlaceFinder = new OccurancePlaceFinder();
	private static final ThrowableHelper throwableHelper = new ThrowableHelper();
	
	//method
	public Error createErrorFromInvocationTargetExceptionInInstance(
		final InvocationTargetException invocationTargetException,
		final Object instance
	) {
		return createErrorFromThrowableInInstance(invocationTargetException.getCause(), instance);
	}
	
	//method
	public Error createErrorFromThrowableInInstance(final Throwable throwable, final Object instance) {
		return
		new Error(
			throwableHelper.getMessageFromThrowableOrDefaultErrorMessage(throwable),
			occurancePlaceFinder.findOccurancePlaceOfThrowableInInstance(throwable, instance)
		);
	}
}
