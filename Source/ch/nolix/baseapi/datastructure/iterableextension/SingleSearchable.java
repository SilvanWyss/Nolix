/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link SingleSearchable}
 */
public interface SingleSearchable<E> {

  /**
   * @param oneBasedIndex
   * @return the element at the given oneBasedIndex. The element can be null
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain an element at the given oneBasedIndex.
   */
  E getStoredAtOneBasedIndex(int oneBasedIndex);

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

  /**
   * @return the last element of the current {@link SingleSearchable}. The element
   *         can be null
   * @throws RuntimeException if the current {@link SingleSearchable} is empty
   */
  E getStoredLast();

  /**
   * @return the one element of the current {@link SingleSearchable}. The element
   *         can be null
   * @throws RuntimeException if the current {@link SingleSearchable} is empty
   * @throws RuntimeException if the current {@link SingleSearchable} contains
   *                          several elements.
   */
  E getStoredOne();

  /**
   * @param selector
   * @return the one element the given selector selects from the current
   *         {@link SingleSearchable}, ignoring null elements
   * @throws RuntimeException if the given selector is null
   * @throws RuntimeException if the given selector selects none or several
   *                          elements from the current {@link SingleSearchable}.
   */
  E getStoredOne(Predicate<? super E> selector);
}
