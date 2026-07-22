/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableLastProvider}
 */
public interface IterableLastProvider<E> {
  /**
   * The time complexity of this method is O(1).
   * 
   * @return the last element of the current {@link IterableLastProvider}
   * @throws RuntimeException if the current {@link IterableLastProvider} is empty
   */
  E getStoredLast();
}
