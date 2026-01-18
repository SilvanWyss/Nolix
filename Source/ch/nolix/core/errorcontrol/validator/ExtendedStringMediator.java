/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

/**
 * An extended string mediator is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ExtendedStringMediator extends StringMediator {
  /**
   * Creates a new extended string mediator for the given argument.
   * 
   * @param value
   */
  private ExtendedStringMediator(final String value) {
    //Calls constructor of the base class.
    super(value);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedStringMediator} for the given argument.
   */
  public static ExtendedStringMediator forArgument(final String argument) {
    return new ExtendedStringMediator(argument);
  }

  /**
   * @param type
   * @return a new {@link StringMediator} for the argument of the current
   *         {@link ExtendedStringMediator}.
   */
  public StringMediator thatIsNamed(final Class<?> type) {
    return new StringMediator(type.getSimpleName(), getStoredArgument());
  }

  /**
   * @param argumentName
   * @return a new string mediator for the argument of this extended string
   *         mediator with the given argument name.
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument name is empty.
   */
  public StringMediator thatIsNamed(final String argumentName) {
    return new StringMediator(argumentName, getStoredArgument());
  }
}
