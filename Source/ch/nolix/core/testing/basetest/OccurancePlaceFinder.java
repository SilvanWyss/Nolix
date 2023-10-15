//package declaration
package ch.nolix.core.testing.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
final class OccurancePlaceFinder {

  // method
  public <O extends Object> OccurancePlace findOccurancePlaceOfStackTraceInClass(
      final StackTraceElement[] stackTrace,
      final Class<O> pClass) {

    Class<?> lClass = pClass;
    while (lClass != null) {

      for (final var ste : stackTrace) {
        if (ste.getClassName().equals(lClass.getName())) {
          return new OccurancePlace(ste.getClassName(), ste.getLineNumber());
        }
      }

      lClass = lClass.getSuperclass();
    }

    throw InvalidArgumentException.forArgument("The given stack trace does not occur in the given test class.");
  }

  // method
  public OccurancePlace findOccurancePlaceOfStackTraceInInstance(
      final StackTraceElement[] stackTrace,
      final BaseTest test) {
    return findOccurancePlaceOfStackTraceInClass(stackTrace, test.getClass());
  }

  // method
  public <O extends Object> OccurancePlace findOccurancePlaceOfThrowableInInstance(
      final Throwable throwable,
      final O instance) {
    return findOccurancePlaceOfThrowableInClass(throwable, instance.getClass());
  }

  // method
  public <O extends Object> OccurancePlace findOccurancePlaceOfThrowableInClass(
      final Throwable throwable,
      final Class<O> pClass) {
    return findOccurancePlaceOfStackTraceInClass(throwable.getStackTrace(), pClass);
  }
}
