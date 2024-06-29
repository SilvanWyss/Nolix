//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A {@link Mediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
abstract class Mediator { //NOSONAR: Mediator does not have abstract methods.

  //constant
  public static final String DEFAULT_ARGUMENT_NAME = "argument";

  //attribute
  private final String argumentName;

  //constructor
  /**
   * Creates a new {@link Mediator} with a default argument name.
   */
  protected Mediator() {

    //Calls other constructor.
    this(DEFAULT_ARGUMENT_NAME);
  }

  //constructor
  /**
   * Creates a new argument {@link Mediator} with the given argumentName.
   * 
   * @param argumentName
   * @throws ArgumentIsNullException  if the given argumentName is null.
   * @throws InvalidArgumentException if the given argumentName is blank.
   */
  protected Mediator(final String argumentName) {

    //Asserts that the given argumentName is not null.
    if (argumentName == null) {
      throw ArgumentIsNullException.forArgumentName("argument name");
    }

    //Asserts that the given argumentName is not blank.
    if (argumentName.isBlank()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "argument name",
        argumentName,
        "is blank");
    }

    //Sets the argumentName of the current Mediator.
    this.argumentName = argumentName;
  }

  //method
  /**
   * @return the argument name of the current {@link Mediator}.
   */
  protected final String getArgumentName() {
    return argumentName;
  }
}
