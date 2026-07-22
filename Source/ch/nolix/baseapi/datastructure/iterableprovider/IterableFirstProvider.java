/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableprovider;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableFirstProvider}
 */
public interface IterableFirstProvider<E> {
  /**
   * The time complexity of this method is O(1).
   * 
   * @return a {@link Optional} with the first element of the current
   *         {@link IterableFirstProvider} if the current
   *         {@link IterableFirstProvider} is not empty and if the first
   *         element of the current {@link IterableFirstProvider} is not null,
   *         an empty {@link Optional} otherwise
   */
  Optional<E> getOptionalStoredFirst();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstProvider} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return a new {@link Optional} with the first element the given selector
   *         selects from the current {@link IterableFirstProvider} if the
   *         current {@link IterableFirstProvider} contains an element the
   *         given selector selects, an empty {@link Optional} otherwise, ignoring
   *         null elements
   */
  Optional<E> getOptionalStoredFirst(Predicate<? super E> selector);

  /**
   * The time complexity of this method is O(1).
   * 
   * @return the first element of the current {@link IterableFirstProvider}
   * @throws RuntimeException if the current {@link IterableFirstProvider} is
   *                          empty
   */
  E getStoredFirst();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstProvider} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return the first element the given selector selects from the current
   *         {@link IterableFirstProvider}, ignoring null elements
   * @throws RuntimeException if the current {@link IterableFirstProvider} does
   *                          not contain an element the given selector selects
   */
  E getStoredFirst(Predicate<? super E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstProvider} contains n elements.
   * 
   * @return the first non-null element of the current
   *         {@link IterableFirstProvider}
   * @throws RuntimeException if the current {@link IterableFirstProvider} does
   *                          not contain a non-null element
   */
  E getStoredFirstNonNull();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFirstProvider} contains n elements.
   * 
   * @param type
   * @param <T>  the modeled type of the given type
   * @return the first element from the current {@link IterableFirstProvider}
   *         that is of the given type
   * @throws RuntimeException if the given type is null
   * @throws RuntimeException if the current {@link IterableFirstProvider} does
   *                          not contain an element of the given type
   */
  <T extends E> T getStoredFirstOfType(Class<T> type);
}
