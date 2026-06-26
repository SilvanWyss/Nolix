/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the argument of a
 *            {@link IterableMediator}.
 */
public final class IterableMediator<E> extends AbstractIterableMediator<E> {
  private IterableMediator(final Iterable<E> argument, final String argumentName) {
    super(argument, argumentName);
  }

  public static <T> IterableMediator<T> forArgumentAndArgumentName(
    final Iterable<T> argument,
    final String argumentName) {
    return new IterableMediator<>(argument, argumentName);
  }
}
