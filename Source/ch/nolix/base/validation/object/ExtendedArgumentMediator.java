/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * An extended generic argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @param <A> is the type of the argument of an extended generic argument
 *            mediator.
 */
public class ExtendedArgumentMediator<A> extends ObjectMediator<A> {
  /**
   * Creates a new extended generic argument mediator for the given argument.
   * 
   * @param argument
   */
  public ExtendedArgumentMediator(final A argument) {
    //Calls constructor of the base class.
    super(argument);
  }

  /**
   * @param type
   * @return a new generic argument mediator with the argument name from the given
   *         type and for the argument of this extended generic argument mediator.
   */
  public final ObjectMediator<A> thatIsNamed(final Class<?> type) {
    return new ObjectMediator<>(type.getSimpleName(), getStoredArgument());
  }

  /**
   * @param argumentName
   * @return a new generic argument mediator with the given argument name and for
   *         the argument of this extended generic argument mediator.
   * @throws RuntimeException if the given argument name is null.
   * @throws RuntimeException if the given argument name is empty.
   */
  public ObjectMediator<A> thatIsNamed(final String argumentName) {
    return new ObjectMediator<>(argumentName, getStoredArgument());
  }
}
