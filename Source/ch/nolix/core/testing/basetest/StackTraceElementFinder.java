//package declaration
package ch.nolix.core.testing.basetest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.independent.container.List;

//class
final class StackTraceElementFinder {

  //method
  public List<StackTraceElement> getStackTraceElementsOfRunningMethod(final Method method) {

    final var stackTraceElements = new List<StackTraceElement>();

    for (var st : Thread.getAllStackTraces().values()) {
      for (var ste : st) {
        if (ste.getMethodName().equals(method.getName())) {
          stackTraceElements.addAtEnd(ste);
        }
      }
    }

    return stackTraceElements;
  }

  //method
  public StackTraceElement getStackTraceElementOfMethodInThrowable(final Method method, final Throwable throwable) {
    return getStackTraceElementOfMethodInStackTrace(method, throwable.getStackTrace());
  }

  //method
  private StackTraceElement getStackTraceElementOfMethodInStackTrace(
      final Method method,
      final StackTraceElement[] stackTrace) {

    for (final var ste : stackTrace) {
      if (ste.getMethodName().equals(method.getName())) {
        return ste;
      }
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(
        method,
        "is not called the given stackTrace '" + stackTrace + "'");
  }
}
