/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * @author Silvan Wyss
 * @param <A> the type of the argument of a {@link NamableObjectMediator}.
 */
public final class NamableObjectMediator<A> extends AbstractObjectMediator<A> {
  /**
   * Creates a new {@link NamableObjectMediator} for the given argumente.
   * 
   * @param argument
   */
  private NamableObjectMediator(final A argument) {
    super(argument);
  }

  /**
   * @param argument
   * @param <T>      is the type of the given argument
   * @return a new {@link NamableObjectMediator} for the given argument.
   */
  public static <T> NamableObjectMediator<T> forArgument(final T argument) {
    return new NamableObjectMediator<>(argument);
  }

  /**
   * @param type
   * @return a new {@link ObjectMediator} for the argument of the current @link
   *         NamableObjectMediator} and the argument name from the given type
   * @throws RuntimeException if the given type is null
   */
  public ObjectMediator<A> thatIsNamed(final Class<?> type) {
    return ObjectMediator.forArgumentAndArgumentName(getStoredArgument(), type.getSimpleName());
  }

  /**
   * @param argumentName
   * @return a new {@link ObjectMediator} for the argument of the current
   *         {@link NamableObjectMediator} and the given argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public ObjectMediator<A> thatIsNamed(final String argumentName) {
    return ObjectMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
