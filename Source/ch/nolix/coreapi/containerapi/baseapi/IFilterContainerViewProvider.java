package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2025-03-09
 * @param <E> is the type of the elements of a {@link IFilterContainerViewProvider}.
 */
public interface IFilterContainerViewProvider<E> {

  /**
   * @param selector
   * @return a new {@link IContainer} view with the elements the given selector
   *         selects from the current {@link IFilterContainerViewProvider}. Ignores null
   *         elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getViewOfStoredSelected(Predicate<E> selector);
}
