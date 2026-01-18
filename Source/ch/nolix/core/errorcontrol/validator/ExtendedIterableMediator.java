/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

/**
 * An extended container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the argument of an extended
 *            container mediator.
 */
public class ExtendedIterableMediator<E> extends IterableMediator<E> {
  /**
   * Creates a new extended container mediator for the given argument.
   * 
   * @param argument
   */
  ExtendedIterableMediator(final Iterable<E> argument) {
    //Calls constructor of the base class.
    super(argument);
  }

  /**
   * @param type
   * @return a new container mediator for the argument of this extended container
   *         mediator.
   */
  public final IterableMediator<E> thatIsInstanceOf(final Class<?> type) {
    return new IterableMediator<>(type.getSimpleName(), getStoredArgument());
  }

  /**
   * @param argumentName
   * @return a new container mediator with the given argument name for the
   *         argument of this extended container mediator.
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument name is empty.
   */
  public IterableMediator<E> thatIsNamed(final String argumentName) {
    return new IterableMediator<>(argumentName, getStoredArgument());
  }
}
