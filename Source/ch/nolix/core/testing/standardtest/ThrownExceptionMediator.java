package ch.nolix.core.testing.standardtest;

import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link ThrownExceptionMediator} is not mutable.
 * 
 * A {@link ThrownExceptionMediator} does not need to have an exception. In the
 * case an exception was expected, but not thrown, a
 * {@link ThrownExceptionMediator} must be created, but an exception cannot be
 * given to it.
 * 
 * @author Silvan Wyss
 * @version 2018-12-09
 */
public class ThrownExceptionMediator {

  private final Throwable exception;

  /**
   * Creates a new {@link ThrownExceptionMediator}.
   */
  ThrownExceptionMediator() {

    //Clears the exception of the current thrown exception mediator.
    exception = null;
  }

  /**
   * Creates a new {@link ThrownExceptionMediator} for the given exception.
   * 
   * @param exception
   * @throws ArgumentIsNullException if the given exception is null.
   */
  ThrownExceptionMediator(final Throwable exception) {

    //Asserts that the given exception is not null.
    if (exception == null) {
      throw ArgumentIsNullException.forArgumentType(Exception.class);
    }

    //Sets the exception of the current thrown exception mediator.
    this.exception = exception;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} does not have a message.
   */
  public final void withMessage() {

    //Handles the case that the current thrown exception mediator has an exception.
    if (exception != null && exception.getMessage() == null) {
      throw //
      GeneralException.withErrorMessage(
        "An exception with a message was expected,"
        + "but an exception without messag was received.");
    }
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} does not have the given message.
   * 
   * @param message
   * @throws ArgumentIsNullException if the given message is null.
   */
  public final void withMessage(final String message) {

    //Asserts that the given message is not null.
    if (message == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.MESSAGE);
    }

    //Handles the case that the current ThrownExceptionMediator has an exception.
    if (exception != null) {

      //Asserts that the exception of the current ThrownExceptionMediator has a
      //message.
      if (exception.getMessage() == null) {
        throw //
        GeneralException.withErrorMessage(
          "An exception with the message '"
          + message
          + "' was expected, but an exception without messag was received.");
      }

      //Asserts that the exception of the current ThrownExceptionMediator has the
      //given message.
      if (!exception.getMessage().equals(message)) {
        throw //
        GeneralException.withErrorMessage(
          "An exception with the message '"
          + message
          + "' was expected, but an exception with the message '"
          + exception.getMessage()
          + "' was thrown.");
      }
    }
  }

  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} does not have a message that matches the
   * given regex
   * 
   * @param regex
   * @throws ArgumentIsNullException if the given regex is null.
   */
  public final void withMessageThatMatches(final String regex) {

    if (regex == null) {
      throw ArgumentIsNullException.forArgumentName("regex");
    }

    final var message = exception.getMessage();

    if (message == null) {
      throw //
      GeneralException.withErrorMessage(
        "An exception with a message that matches the regex '"
        + regex
        + "' was expected, but an exception without message was thrown.");
    } else if (!message.matches(regex)) {
      throw //
      GeneralException.withErrorMessage(
        "An exception with a message that matches the regex '"
        + regex
        + "' was expected, but an exception with the message '"
        + message
        + "' was thrown.");
    } else {
      //Does nothing because there was not found any error.
    }
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} has a message.
   */
  public final void withoutMessage() {

    //Handles the case that the current ThrownExceptionMediator has an exception.
    if (exception != null && exception.getMessage() != null) {
      throw //
      GeneralException.withErrorMessage(
        "An exception without message was expected, but an exception with the message '"
        + exception.getMessage()
        + "' was received.");
    }
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the exception of the current {@link ThrownExceptionMediator}.
   * 
   */
  final Throwable getException() {

    //Asserts that the current ThrownExceptionMediator has an exception.
    if (exception == null) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.EXCEPTION);
    }

    return exception;
  }

  /**
   * @return true if the current {@link ThrownExceptionMediator} has an exception.
   */
  final boolean hasException() {
    return (exception != null);
  }
}
