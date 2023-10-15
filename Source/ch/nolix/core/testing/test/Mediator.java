//package declaration
package ch.nolix.core.testing.test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
/**
 * @author Silvan Wyss
 * @date 2017-01-08
 */
public abstract class Mediator { // NOSONAR: Mediator does not have abstract methods.

  // attribute
  private final IElementTaker<String> expectationErrorTaker;

  // constructor
  /**
   * Creates a new {@link Mediator} with the given expectationError.
   * 
   * @param expectationErrorTaker
   * @throws ArgumentIsNullException if the given expectationErrorTaker is null.
   */
  protected Mediator(final IElementTaker<String> expectationErrorTaker) {

    // Asserts that the given expectationErrorTaker is not null.
    if (expectationErrorTaker == null) {
      throw ArgumentIsNullException.forArgumentName("expectation error taker");
    }

    // Sets the expectationErrorTaker of the current Mediator.
    this.expectationErrorTaker = expectationErrorTaker;
  }

  // method
  /**
   * @throws IllegalCallerException
   */
  @Override
  public final boolean equals(final Object object) {
    throw new IllegalCallerException(
        "Do not use the equals method of a mediator. For expecting equality, use the 'isEqualTo' method.");
  }

  // method
  @Override
  public int hashCode() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "hashCode");
  }

  // method
  /**
   * Adds the given current test case error to the test this mediator belongs to.
   * 
   * @param error
   */
  protected final void addCurrentTestCaseError(final String error) {
    expectationErrorTaker.run(error);
  }

  // method
  /**
   * @return the expectationErrorTaker of the current {@link Mediator}.
   */
  protected final IElementTaker<String> getStoredExpectationErrorTaker() {
    return expectationErrorTaker;
  }
}
