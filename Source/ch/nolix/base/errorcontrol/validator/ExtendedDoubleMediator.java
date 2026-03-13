/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;

/**
 * An extended double mediator is not mutable.
 * 
 * @author Silvan Wyss
 */
public class ExtendedDoubleMediator extends DoubleMediator {
  /**
   * Creates a new extended double mediator for the given argument.
   * 
   * @param argument
   */
  ExtendedDoubleMediator(final double argument) {
    //Calls constructor of the base class.
    super(argument);
  }

  /**
   * @param argumentName
   * @return a new double mediator with the given argument name for the argument
   *         of this extended double mediator.
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument name is empty.
   */
  public DoubleMediator thatIsNamed(final String argumentName) {
    return new DoubleMediator(argumentName, getArgument());
  }
}
