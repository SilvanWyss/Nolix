package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2025-05-30
 * @param <E> is the type of the elements a {@link IFilter}.
 */
public interface IFilter<E> {

  /**
   * @param type
   * @param <E2> is the type of the elements of the returned {@link IContainer}.
   * @return a new {@link IContainer} with the elements from the current
   *         {@link IFilter} that are of the given type. Ignores null elements.
   * @throws RuntimeException if the given type is null.
   */
  <E2 extends E> IContainer<E2> getStoredOfType(Class<E2> type);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements from the current
   *         {@link IFilter} the given selector skips. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredOthers(Predicate<E> selector);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements the given selector selects
   *         from the current {@link IFilter}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredSelected(Predicate<? super E> selector);
}
