//package declaration
package ch.nolix.core.testing.test;

//Java imports
import java.util.function.Consumer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
/**
 * A {@link ClosureMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public final class ClosureMediator extends Mediator {

  //attribute
  private final Runnable closure;

  //constructor
  /**
   * Creates a new {@link ClosureMediator} that belongs to the given test and is
   * for the given closure.
   * 
   * @param expectationErrorTaker
   * @param closure
   * @throws ArgumentIsNullException if the given test is null.
   * @throws ArgumentIsNullException if the given closure is null.
   */
  public ClosureMediator(final Consumer<String> expectationErrorTaker, final Runnable closure) {

    //Calls constructor of the base class.
    super(expectationErrorTaker);

    //Asserts that the given closure is not null.
    if (closure == null) {
      throw ArgumentIsNullException.forArgumentName("closure");
    }

    //Sets the closure of the current ClosureMediator.
    this.closure = closure;
  }

  //method
  /**
   * Generates an error if the closure of the current {@link ClosureMediator} does
   * not throw an exception.
   * 
   * @return a new {@link ExtendedThrownExceptionMediator}.
   */
  public ExtendedThrownExceptionMediator throwsException() {
    try {
      closure.run();
      addCurrentTestCaseError("An exception was expected, but no exception was thrown.");
      return new ExtendedThrownExceptionMediator(getStoredExpectationErrorTaker());
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught here.
      return new ExtendedThrownExceptionMediator(getStoredExpectationErrorTaker(), exception);
    }
  }

  //method
  /**
   * Generates an error if the closure of the current {@link ClosureMediator}
   * throws an exception.
   */
  public void doesNotThrowException() {
    try {
      closure.run();
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught here.

      final var message = exception.getMessage();

      if (message == null || message.isBlank()) {
        addCurrentTestCaseError(
          "An exception was not expected, but a "
          + exception.getClass().getName()
          + " was thrown.");
      } else {
        addCurrentTestCaseError(
          "An exception was not expected, but a "
          + exception.getClass().getName()
          + " was thrown with the message '"
          + message
          + "'");
      }
    }
  }
}
