/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.base;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractMediator { // NOSONAR: A AbstractMediator does not have abstract methods.
  public static final String DEFAULT_ARGUMENT_NAME = "argument";

  private final String argumentName;

  /**
   * Creates a new {@link AbstractMediator} with a default argument name.
   */
  protected AbstractMediator() {
    argumentName = DEFAULT_ARGUMENT_NAME;
  }

  /**
   * Creates a new argument {@link AbstractMediator} with the given argumentName.
   * 
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank
   */
  protected AbstractMediator(final String argumentName) {
    if (argumentName == null) {
      throw ArgumentIsNullException.forArgumentName("argument name");
    }

    if (argumentName.isBlank()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(argumentName, "argument name", "is blank");
    }

    this.argumentName = argumentName;
  }

  /**
   * @return the argument name of the current {@link AbstractMediator}.
   */
  protected final String getArgumentName() {
    return argumentName;
  }
}
