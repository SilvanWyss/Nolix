/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link IterableFilterViewProvider}
 */
public interface IterableFilterViewProvider<E> {
  /**
   * @param selector
   * @return a new {@link ExtendedIterable} view with the elements the given
   *         selector selects from the current
   *         {@link IterableFilterViewProvider}, ignoring null elements
   * @throws RuntimeException if the given selector is null
   */
  ExtendedIterable<E> getViewOfStoredSelected(Predicate<E> selector);
}
