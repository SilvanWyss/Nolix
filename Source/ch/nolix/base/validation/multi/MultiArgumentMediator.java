/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.base.foundation.arrayiterableview.ArrayIterableView;

/**
 * A {@link MultiArgumentMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <A> the type of the arguments of a {@link MultiArgumentMediator}
 */
public final class MultiArgumentMediator<A> extends AbstractMultiArgumentMediator<A> {
  /**
   * Creates a new {@link MultiArgumentMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null
   */
  private MultiArgumentMediator(final Iterable<A> arguments) {
    super(arguments);
  }

  /**
   * @param arguments
   * @param <T>       the type of the arguments of the created
   *                  {@link MultiArgumentMediator}
   * @return a new {@link MultiArgumentMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static <T> MultiArgumentMediator<T> forArguments(final T[] arguments) {
    final var argumentsIterableView = ArrayIterableView.forArray(arguments);

    return new MultiArgumentMediator<>(argumentsIterableView);
  }

  /**
   * @param arguments
   * @param <T>       the type of the arguments of the created
   *                  {@link MultiArgumentMediator}
   * @return a new {@link MultiArgumentMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static <T> MultiArgumentMediator<T> forArguments(final Iterable<T> arguments) {
    return new MultiArgumentMediator<>(arguments);
  }
}
