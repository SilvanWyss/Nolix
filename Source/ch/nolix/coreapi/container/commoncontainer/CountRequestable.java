/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.container.commoncontainer;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements a {@link CountRequestable}.
 */
public interface CountRequestable<E> {
  /**
   * @return the number of elements of the current {@link CountRequestable}.
   */
  int getCount();

  /**
   * @param selector
   * @return the number of elements the given selector selects from the current
   *         {@link CountRequestable}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  int getCount(Predicate<E> selector);

  /**
   * @param element
   * @return the number how many times the current {@link CountRequestable}
   *         contains the given element.
   */
  int getCountOf(Object element);
}
