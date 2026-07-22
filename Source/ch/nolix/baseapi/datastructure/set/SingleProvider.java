/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.set;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link SingleProvider}
 */
public interface SingleProvider<E> {
  /**
   * The time complexity of this method is O(1).
   * 
   * @return the single element of the current {@link SingleProvider}
   * @throws RuntimeException if the current {@link SingleProvider} is
   *                          empty or contains several elements
   */
  E getStoredSingle();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link SingleProvider} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return the one element the given selector selects from the current
   *         {@link SingleProvider}, ignoring null elements
   * @throws RuntimeException if the given the current
   *                          {@link SingleProvider} contains none or
   *                          several elements the given selector selects
   */
  E getStoredSingle(Predicate<? super E> selector);
}
