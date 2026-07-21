/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.set;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link CountRequestable}.
 */
public interface CountRequestable<E> extends BaseCountRequestable {
  /**
   * @param selector
   * @return the number of elements the given selector selects from the current
   *         {@link CountRequestable}, ignoring null elements
   * @throws RuntimeException if the given selector is null
   */
  int getCount(Predicate<E> selector);

  /**
   * @param element
   * @return the number how many times the current {@link CountRequestable}
   *         contains the given element
   */
  int getCountOf(Object element);
}
