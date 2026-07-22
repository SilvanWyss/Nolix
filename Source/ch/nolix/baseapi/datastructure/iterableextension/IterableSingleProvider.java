/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableSingleProvider}
 */
public interface IterableSingleProvider<E> {
  /**
   * The time complexity of this method is O(1).
   * 
   * @return the single element of the current {@link IterableSingleProvider}
   * @throws RuntimeException if the current {@link IterableSingleProvider} is
   *                          empty or contains several elements
   */
  E getStoredSingle();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableSingleProvider} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return the one element the given selector selects from the current
   *         {@link IterableSingleProvider}, ignoring null elements
   * @throws RuntimeException if the given the current
   *                          {@link IterableSingleProvider} contains none or
   *                          several elements the given selector selects
   */
  E getStoredSingle(Predicate<? super E> selector);
}
