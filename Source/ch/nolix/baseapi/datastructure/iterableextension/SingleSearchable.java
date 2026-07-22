/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link SingleSearchable}
 */
public interface SingleSearchable<E> {
  /**
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link SingleSearchable}
   * @return the element with the biggest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link SingleSearchable}, ignoring null elements
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMax(Function<E, C> comparableMapper);

  /**
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link SingleSearchable}
   * @return the element with the smallest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link SingleSearchable}, ignoring null elements
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMin(Function<E, C> comparableMapper);

}
