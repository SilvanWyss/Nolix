//package declaration
package ch.nolix.core.testing.test;

import java.util.function.Consumer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link ThrownExceptionMediator} is not mutable.
 * 
 * A {@link ThrownExceptionMediator} does not need to have an exception. In the
 * case an exception was expected, but not thrown, a
 * {@link ThrownExceptionMediator} must be created, but an exception cannot be
 * given to it.
 * 
 * @author Silvan Wyss
 * @date 2018-12-09
 */
public abstract class ThrownExceptionMediator extends Mediator {

  //optional attribute
  private final Throwable exception;

  //constructor
  /**
   * Creates a new {@link ThrownExceptionMediator} that will belong to the given
   * test.
   * 
   * @param expectationErrorTaker
   * @throws ArgumentIsNullException if the given test is null.
   */
  ThrownExceptionMediator(final Consumer<String> expectationErrorTaker) {

    //Calls constructor of the base class.
    super(expectationErrorTaker);

    //Clears the exception of the current thrown exception mediator.
    exception = null;
  }

  //constructor
  /**
   * Creates a new {@link ThrownExceptionMediator} that will belong to the given
   * test and is for the given exception.
   * 
   * @param expectationErrorTaker
   * @param exception
   * @throws ArgumentIsNullException if the given test is null.
   * @throws ArgumentIsNullException if the given exception is null.
   */
  ThrownExceptionMediator(final Consumer<String> expectationErrorTaker, final Throwable exception) {

    //Calls constructor of the base class.
    super(expectationErrorTaker);

    //Asserts that the given exception is not null.
    if (exception == null) {
      throw ArgumentIsNullException.forArgumentType(Exception.class);
    }

    //Sets the exception of the current thrown exception mediator.
    this.exception = exception;
  }

  //method
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} does not have a message.
   */
  public final void withMessage() {

    //Handles the case that the current thrown exception mediator has an exception.
    //For a better performance, this implementation does not use all comfortable
    //methods.
    if (exception != null && exception.getMessage() == null) {
      addCurrentTestCaseError(
          "An exception with a message was expected,"
              + "but an exception without messag was received.");
    }
  }

  //method
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
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.MESSAGE);
    }

    //Handles the case that the current ThrownExceptionMediator has an exception.
    //For a better performance, this implementation does not use all comfortable
    //methods.
    if (exception != null) {

      //Asserts that the exception of the current ThrownExceptionMediator has a
      //message.
      if (exception.getMessage() == null) {
        addCurrentTestCaseError(
            "An exception with the message '"
                + message
                + "' was expected, but an exception without messag was received.");
      }

      //Asserts that the exception of the current ThrownExceptionMediator has the
      //given message.
      if (!exception.getMessage().equals(message)) {
        addCurrentTestCaseError(
            "An exception with the message '"
                + message
                + "' was expected, but an exception with the message '"
                + exception.getMessage()
                + "' was thrown.");
      }
    }
  }

  //method
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} has a message.
   */
  public final void withoutMessage() {

    //Handles the case that the current ThrownExceptionMediator has an exception.
    //For a better performance, this implementation does not use all comfortable
    //methods.
    if (exception != null && exception.getMessage() != null) {
      addCurrentTestCaseError(
          "An exception without message was expected, but an exception with the message '"
              + exception.getMessage()
              + "' was received.");
    }
  }

  //method
  /**
   * @return the exception of the current {@link ThrownExceptionMediator}.
   * 
   */
  final Throwable getException() {

    //Asserts that the current ThrownExceptionMediator has an exception.
    //For a better performance, this implementation does not use all comfortable
    //methods.
    if (exception == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.EXCEPTION);
    }

    return exception;
  }

  //method
  /**
   * @return true if the current {@link ThrownExceptionMediator} has an exception.
   */
  final boolean hasException() {
    return (exception != null);
  }
}
