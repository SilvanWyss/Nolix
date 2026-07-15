/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.standardtest;

import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractThrownExceptionMediator { //NOSONAR: A AbstractThrownExceptionMediator does not have abstract methods.
  private final Throwable nullableException;

  /**
   * Creates a new {@link AbstractThrownExceptionMediator} without exception.
   */
  protected AbstractThrownExceptionMediator() {
    nullableException = null;
  }

  /**
   * Creates a new {@link AbstractThrownExceptionMediator} for the given
   * exception.
   * 
   * @param exception
   * @throws RuntimeException if the given exception is null.
   */
  protected AbstractThrownExceptionMediator(final Throwable exception) {
    // Asserts that the given exception is not null.
    if (exception == null) {
      throw ArgumentIsNullException.forArgumentType(Exception.class);
    }

    // Sets the exception of the current thrown exception mediator.
    this.nullableException = exception;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link AbstractThrownExceptionMediator} does not have a message.
   */
  public final void withMessage() {
    // Handles the case that the current thrown exception mediator has an exception.
    if (nullableException != null && nullableException.getMessage() == null) {
      throw //
      GeneralException.withErrorMessage(
        "An exception with a message was expected,"
        + "but an exception without messag was received.");
    }
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link AbstractThrownExceptionMediator} does not have the given message.
   * 
   * @param message
   * @throws RuntimeException if the given message is null.
   */
  public final void withMessage(final String message) {
    // Asserts that the given message is not null.
    if (message == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.MESSAGE);
    }

    // Handles the case that the current AbstractThrownExceptionMediator has an exception.
    if (nullableException != null) {
      // Asserts that the exception of the current AbstractThrownExceptionMediator has a
      // message.
      if (nullableException.getMessage() == null) {
        throw //
        GeneralException.withErrorMessage(
          "An exception with the message '"
          + message
          + "' was expected, but an exception without messag was received.");
      }

      // Asserts that the exception of the current AbstractThrownExceptionMediator has the
      // given message.
      if (!nullableException.getMessage().equals(message)) {
        throw //
        GeneralException.withErrorMessage(
          "An exception with the message '"
          + message
          + "' was expected, but an exception with the message '"
          + nullableException.getMessage()
          + "' was thrown.");
      }
    }
  }

  /**
   * Generates an error if the exception of the current
   * {@link AbstractThrownExceptionMediator} does not have a message that matches
   * the given regex
   * 
   * @param regex
   * @throws RuntimeException if the given regex is null.
   */
  public final void withMessageThatMatches(final String regex) {
    if (regex == null) {
      throw ArgumentIsNullException.forArgumentName("regex");
    }

    final var message = nullableException.getMessage();

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
      // Does nothing because there was not found any error.
    }
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Generates an error if the exception of the current
   * {@link AbstractThrownExceptionMediator} has a message.
   */
  public final void withoutMessage() {
    // Handles the case that the current AbstractThrownExceptionMediator has an exception.
    if (nullableException != null && nullableException.getMessage() != null) {
      throw //
      GeneralException.withErrorMessage(
        "An exception without message was expected, but an exception with the message '"
        + nullableException.getMessage()
        + "' was received.");
    }
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the exception of the current {@link AbstractThrownExceptionMediator}.
   * 
   */
  protected final Throwable getException() {
    // Asserts that the current AbstractThrownExceptionMediator has an exception.
    if (nullableException == null) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.EXCEPTION);
    }

    return nullableException;
  }

  /**
   * @return true if the current {@link AbstractThrownExceptionMediator} has an
   *         exception, false otherwise.
   */
  protected final boolean hasException() {
    return nullableException != null;
  }
}
