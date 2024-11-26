package ch.nolix.coreapi.containerapi.commoncontainerapi;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2024-11-26
 * @param <E> is the type of the elements a {@link ICountingContainer}.
 */
public interface ICountingContainer<E> {

  /**
   * @return the number of elements of the current {@link ICountingContainer}.
   */
  int getCount();

  /**
   * @param selector
   * @return the number of elements the given selector selects from the current
   *         {@link ICountingContainer}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  int getCount(Predicate<E> selector);

  /**
   * @param element
   * @return the number how many times the current {@link ICountingContainer}
   *         contains the given element.
   */
  int getCountOf(Object element);
}
