package ch.nolix.core.testing.standardtest;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * A {@link ClosureMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-10-01
 */
public final class ClosureMediator {

  private final Runnable closure;

  /**
   * Creates a new {@link ClosureMediator} for the given closure.
   * 
   * @param closure
   * @throws ArgumentIsNullException if the given closure is null.
   */
  public ClosureMediator(final Runnable closure) {

    //Asserts that the given closure is not null.
    if (closure == null) {
      throw ArgumentIsNullException.forArgumentName("closure");
    }

    //Sets the closure of the current ClosureMediator.
    this.closure = closure;
  }

  /**
   * Generates an error if the closure of the current {@link ClosureMediator} does
   * not throw an exception.
   * 
   * @return a new {@link ExtendedThrownExceptionMediator}.
   */
  public ExtendedThrownExceptionMediator throwsException() {
    try {
      closure.run();
      throw GeneralException.withErrorMessage("An exception was expected, but no exception was thrown.");
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught.
      return new ExtendedThrownExceptionMediator(exception);
    }
  }

  /**
   * Generates an error if the closure of the current {@link ClosureMediator}
   * throws an exception.
   */
  public void doesNotThrowException() {
    try {
      closure.run();
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught.

      final var message = exception.getMessage();

      if (message == null || message.isBlank()) {
        throw //
        GeneralException.withErrorMessage(
          "An exception was not expected, but a "
          + exception.getClass().getName()
          + " was thrown.");
      } else {
        throw //
        GeneralException.withErrorMessage(
          "An exception was not expected, but a "
          + exception.getClass().getName()
          + " was thrown with the message '"
          + message
          + "'");
      }
    }
  }
}
