/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableFilterProvider}
 */
public interface IterableFilterProvider<E> {
  /**
   * @param type
   * @param <T>  the type of the elements of the returned {@link ExtendedIterable}
   * @return a new {@link ExtendedIterable} with the elements from the current
   *         {@link IterableFilterProvider} that are of the given type, ignoring
   *         null elements
   * @throws RuntimeException if the given type is null
   */
  <T extends E> ExtendedIterable<T> getStoredOfType(Class<T> type);

  /**
   * @param selector
   * @return a new {@link ExtendedIterable} with the elements from the current
   *         {@link IterableFilterProvider} the given selector skips, ignoring
   *         null elements
   * @throws RuntimeException if the given selector is null
   */
  ExtendedIterable<E> getStoredOthers(Predicate<E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableFilterProvider} contains n elements.
   * 
   * @param selector
   * @return a new {@link ExtendedIterable} with the elements the given selector
   *         selects from the current {@link IterableFilterProvider}, ignoring
   *         null elements
   * @throws RuntimeException if the given selector is null
   */
  ExtendedIterable<E> getStoredSelected(Predicate<? super E> selector);
}
