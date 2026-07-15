/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.standardtest;

import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link ExtendedThrownExceptionMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ExtendedThrownExceptionMediator extends AbstractThrownExceptionMediator {
  /**
   * Creates a new {@link ExtendedThrownExceptionMediator} for the given
   * exception.
   * 
   * @param exception
   * @throws RuntimeException if the given exception is null.
   */
  private ExtendedThrownExceptionMediator(final Throwable exception) {
    // Calls constructor of the base class.
    super(exception);
  }

  /**
   * @param exception
   * @return a new {@link ExtendedThrownExceptionMediator} for the given
   *         exception.
   * @throws RuntimeException if the given exception is null.
   */
  public static ExtendedThrownExceptionMediator forExcetpion(final Throwable exception) {
    return new ExtendedThrownExceptionMediator(exception);
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
   * @throws RuntimeException if the given type is null.
   */
  public <E extends Exception> ThrownExceptionMediator ofType(final Class<E> type) {
    // Asserts that the given type is not null.
    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.TYPE);
    }

    // Handles the case that the current extended thrown exception mediator
    // does not have an exception.
    if (!hasException()) {
      return ThrownExceptionMediator.withoutException();
    }

    // Handles the case that the current extended thrown exception mediator has an
    // exception.
    if (!type.isAssignableFrom(getException().getClass())) {
      throw GeneralException.withErrorMessage(
        "An exception of the type "
        + type.getName()
        + " was expected, but an exception of the type "
        + getException().getClass().getName()
        + " was thrown.");
    }

    return ThrownExceptionMediator.forExcetpion(getException());
  }
}
