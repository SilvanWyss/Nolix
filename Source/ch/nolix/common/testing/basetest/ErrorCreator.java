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
	public Error createErrorFromInvocationTargetExceptionInTest(
		final InvocationTargetException invocationTargetException,
		final BaseTest test
	) {
		return createErrorFromThrowableInTest(invocationTargetException.getCause(), test);
	}
	
	//method
	public Error createErrorFromThrowableInTest(final Throwable throwable, final BaseTest test) {
		return
		new Error(
			throwableHelper.getMessageFromThrowableOrDefaultErrorMessage(throwable),
			occurancePlaceFinder.findOccurancePlaceOfThrowableInTest(throwable, test)
		);
	}
}
