//package declaration
package ch.nolix.core.testing.test;

import java.util.function.Consumer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link ExtendedThrownExceptionMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2018-12-09
 */
public final class ExtendedThrownExceptionMediator extends ThrownExceptionMediator {

  //constructor
  /**
   * Creates a new {@link ExtendedThrownExceptionMediator} that will belong to the
   * given test.
   * 
   * @param expectationErrorTaker
   * @throws ArgumentIsNullException if the given test is null.
   */
  ExtendedThrownExceptionMediator(final Consumer<String> expectationErrorTaker) {

    //Calls constructor of the base class.
    super(expectationErrorTaker);
  }

  //constructor
  /**
   * Creates a new {@link ExtendedThrownExceptionMediator} that will belong to the
   * given test and is for the given exception.
   * 
   * @param expectationErrorTaker
   * @param exception
   * @throws ArgumentIsNullException if the given test is null.
   * @throws ArgumentIsNullException if the given exception is null.
   */
  ExtendedThrownExceptionMediator(final Consumer<String> expectationErrorTaker, final Throwable exception) {

    //Calls constructor of the base class.
    super(expectationErrorTaker, exception);
  }

  //method
  /**
   * Generates an error if the exception of the current
   * {@link ThrownExceptionMediator} is not of the given type.
   * 
   * @param type
   * @param <E>  is the given {@link Exception} type.
   * @return a new {@link ExtendedThrownExceptionMediator} that belongs to the
   *         test of the current {@link ThrownExceptionMediator} and is for the
   *         exception of the current {@link ThrownExceptionMediator}.
   * @throws ArgumentIsNullException if the given type is null.
   */
  public <E extends Exception> ExtendedThrownExceptionMediator ofType(final Class<E> type) {

    //Asserts that the given type is not null.
    if (type == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.TYPE);
    }

    //Handles the case that the current extended thrown exception mediator
    //does not have an exception.
    if (!hasException()) {
      return new ExtendedThrownExceptionMediator(getStoredExpectationErrorTaker());
    }

    //Handles the case that the current extended thrown exception mediator has an
    //exception.
    if (!type.isAssignableFrom(getException().getClass())) {
      addCurrentTestCaseError(
          "An exception of the type "
              + type.getName()
              + " was expected, but an exception of the type "
              + getException().getClass().getName()
              + " was thrown.");
    }

    return new ExtendedThrownExceptionMediator(getStoredExpectationErrorTaker(), getException());
  }
}
