/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.base;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * A {@link AbstractMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public abstract class AbstractMediator { //NOSONAR: Mediator does not have abstract methods.

  public static final String DEFAULT_ARGUMENT_NAME = "argument";

  private final String argumentName;

  /**
   * Creates a new {@link AbstractMediator} with a default argument name.
   */
  protected AbstractMediator() {
    //Calls other constructor.
    this(DEFAULT_ARGUMENT_NAME);
  }

  /**
   * Creates a new argument {@link AbstractMediator} with the given argumentName.
   * 
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null.
   * @throws RuntimeException if the given argumentName is blank.
   */
  protected AbstractMediator(final String argumentName) {
    //Asserts that the given argumentName is not null.
    if (argumentName == null) {
      throw ArgumentIsNullException.forArgumentName("argument name");
    }

    //Asserts that the given argumentName is not blank.
    if (argumentName.isBlank()) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        "argument name",
        argumentName,
        "is blank");
    }

    //Sets the argumentName of the current Mediator.
    this.argumentName = argumentName;
  }

  /**
   * @return the argument name of the current {@link AbstractMediator}.
   */
  protected final String getArgumentName() {
    return argumentName;
  }
}
