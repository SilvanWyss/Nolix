/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableByExtremumProvider}
 */
public interface IterableByExtremumProvider<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableByExtremumProvider} contains n elements.
   * 
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link IterableByExtremumProvider}
   * @return the last (!) element with the biggest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link IterableByExtremumProvider}, ignoring null elements
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if the current {@link IterableByExtremumProvider}
   *                          does not contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMax(Function<E, C> comparableMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableByExtremumProvider} contains n elements.
   * 
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link IterableByExtremumProvider}
   * @return the first (!) element with the smallest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link IterableByExtremumProvider}, ignoring null elements
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if the current {@link IterableByExtremumProvider}
   *                          does not contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMin(Function<E, C> comparableMapper);
}
