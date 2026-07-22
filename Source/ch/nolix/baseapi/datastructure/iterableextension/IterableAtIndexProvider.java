/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableAtIndexProvider}
 */
public interface IterableAtIndexProvider<E> {
  /**
   * @param oneBasedIndex
   * @return the element at the given oneBasedIndex from the current
   *         {@link IterableAtIndexProvider}
   * @throws RuntimeException if the current {@link IterableAtIndexProvider} does
   *                          not contain an element at the given oneBasedIndex
   */
  E getStoredAtOneBasedIndex(int oneBasedIndex);
}
