/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.container.base;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements a {@link Filterable}.
 */
public interface Filterable<E> {
  /**
   * @param type
   * @param <T>  is the type of the elements of the returned {@link IContainer}.
   * @return a new {@link IContainer} with the elements from the current
   *         {@link Filterable} that are of the given type. Ignores null elements.
   * @throws RuntimeException if the given type is null.
   */
  <T extends E> IContainer<T> getStoredOfType(Class<T> type);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements from the current
   *         {@link Filterable} the given selector skips. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredOthers(Predicate<E> selector);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements the given selector selects
   *         from the current {@link Filterable}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredSelected(Predicate<? super E> selector);
}
