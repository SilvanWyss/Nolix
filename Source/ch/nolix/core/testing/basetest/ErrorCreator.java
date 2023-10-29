//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.InvocationTargetException;

//class
final class ErrorCreator {

  //constant
  private static final OccurancePlaceFinder OCCURANCE_PLACE_FINDER = new OccurancePlaceFinder();

  //constant
  private static final ThrowableHelper THROWABLE_HELPER = new ThrowableHelper();

  //method
  public Error createErrorFromInvocationTargetExceptionInInstance(
    final InvocationTargetException invocationTargetException,
    final Object instance) {
    return createErrorFromThrowableInInstance(invocationTargetException.getCause(), instance);
  }

  //method
  public Error createErrorFromThrowableInInstance(final Throwable throwable, final Object instance) {
    return new Error(
      THROWABLE_HELPER.getMessageFromThrowableOrDefaultErrorMessage(throwable),
      OCCURANCE_PLACE_FINDER.findOccurancePlaceOfThrowableInInstance(throwable, instance));
  }
}
