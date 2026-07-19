/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableFirstRequestable}
 */
public interface IterableFirstRequestable<E> {
  /**
   * The time complexity of this method is O(1).
   * 
   * @return a {@link Optional} with the first element of the current
   *         {@link IterableFirstRequestable} if the current
   *         {@link IterableFirstRequestable} is not empty and if the first
   *         element of the current {@link IterableFirstRequestable} is not null,
   *         an empty {@link Optional} otherwise
   */
  Optional<E> getOptionalStoredFirst();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return a new {@link Optional} with the first element the given selector
   *         selects from the current {@link IterableFirstRequestable} if the
   *         current {@link IterableFirstRequestable} contains an element the
   *         given selector selects, an empty {@link Optional} otherwise, ignoring
   *         null elements
   */
  Optional<E> getOptionalStoredFirst(Predicate<? super E> selector);

  /**
   * The time complexity of this method is O(1).
   * 
   * @return the first element of the current {@link IterableFirstRequestable}
   * @throws RuntimeException if the current {@link IterableFirstRequestable} is
   *                          empty
   */
  E getStoredFirst();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return the first element the given selector selects from the current
   *         {@link IterableFirstRequestable}, ignoring null elements
   * @throws RuntimeException if the current {@link IterableFirstRequestable} does
   *                          not contain an element the given selector selects
   */
  E getStoredFirst(Predicate<? super E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstRequestable} contains n elements.
   * 
   * @return the first non-null element of the current
   *         {@link IterableFirstRequestable}
   * @throws RuntimeException if the current {@link IterableFirstRequestable} does
   *                          not contain a non-null element
   */
  E getStoredFirstNonNull();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstRequestable} contains n elements.
   * 
   * @param type
   * @param <T>  the modeled type of the given type
   * @return the first element from the current {@link IterableFirstRequestable}
   *         that is of the given type
   * @throws RuntimeException if the given type is null
   * @throws RuntimeException if the current {@link IterableFirstRequestable} does
   *                          not contain an element of the given type
   */
  <T extends E> T getStoredFirstOfType(Class<T> type);
}
