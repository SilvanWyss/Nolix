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

  public static <T> NamableIterableMediator<T> forArgument(final Iterable<T> argument) {
    return new NamableIterableMediator<>(argument);
  }

  public IterableMediator<E> thatIsNamed(final String argumentName) {
    return IterableMediator.forArgumentAndArgumentName(getStoredArgument(), argumentName);
  }
}
