/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the argument of a
 *            {@link NamableIterableMediator}.
 */
public final class NamableIterableMediator<E> extends AbstractIterableMediator<E> {
  private NamableIterableMediator(final Iterable<E> argument) {
    super(argument);
  }

  /**
   * @param argument
   * @param <T>      the type of the elements of the given argument
   * @return a new {@link NamableIterableMediator} for the given argument
   */
  public static <T> NamableIterableMediator<T> forArgument(final Iterable<T> argument) {
    return new NamableIterableMediator<>(argument);
  }

  /**
   * @param argumentName
   * @return a new {@link IterableMediator} for the argument of the current
   *         {@link NamableIterableMediator} with the given argumentName
   */
  public IterableMediator<E> thatIsNamed(final String argumentName) {
    return IterableMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
