package ch.nolix.core.testing.standardtest;

import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link ExtendedThrownExceptionMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2018-12-09
 */
public final class ExtendedThrownExceptionMediator extends ThrownExceptionMediator {

  /**
   * Creates a new {@link ExtendedThrownExceptionMediator}.
   */
  ExtendedThrownExceptionMediator() {
  }

  /**
   * Creates a new {@link ExtendedThrownExceptionMediator} for the given
   * exception.
   * 
   * @param exception
   * @throws ArgumentIsNullException if the given exception is null.
   */
  ExtendedThrownExceptionMediator(final Throwable exception) {

    //Calls constructor of the base class.
    super(exception);
  }

  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} is not of the given type.
   * 
   * @param type
   * @param <E>  is the given {@link Exception} type.
   * @return a new {@link ThrownExceptionMediator} that belongs to the test of the
   *         current {@link ThrownExceptionMediator} and is for the exception of
   *         the current {@link ThrownExceptionMediator}.
   * @throws ArgumentIsNullException if the given type is null.
   */
  public <E extends Exception> ThrownExceptionMediator ofType(final Class<E> type) {

    //Asserts that the given type is not null.
    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.TYPE);
    }

    //Handles the case that the current extended thrown exception mediator
    //does not have an exception.
    if (!hasException()) {
      return new ExtendedThrownExceptionMediator();
    }

    //Handles the case that the current extended thrown exception mediator has an
    //exception.
    if (!type.isAssignableFrom(getException().getClass())) {
      throw GeneralException.withErrorMessage(
        "An exception of the type "
        + type.getName()
        + " was expected, but an exception of the type "
        + getException().getClass().getName()
        + " was thrown.");
    }

    return new ExtendedThrownExceptionMediator(getException());
  }
}
