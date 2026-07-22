/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableOrderProvider}
 */
public interface IterableOrderProvider<E> {
  /**
   * The time complexity of this method is O(n*log(n)) if the current
   * {@link IterableOrderProvider} contains n elements.
   * 
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper returns
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link IterableOrderProvider} ordered from the smallest to the
   *         biggest element according to the {@link Comparable}s the given
   *         comparableMapper maps from the elements of the current
   *         {@link IterableOrderProvider}
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if one of the elements of the current
   *                          {@link IterableOrderProvider} is null
   */
  <C extends Comparable<C>> ExtendedIterable<E> toOrdered(Function<E, C> comparableMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableOrderProvider} contains n elements.
   * 
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link IterableOrderProvider} in reversed order
   */
  ExtendedIterable<E> toReversed();
}
