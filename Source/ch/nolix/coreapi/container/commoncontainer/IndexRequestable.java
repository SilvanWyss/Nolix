package ch.nolix.coreapi.container.commoncontainer;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2024-11-12
 * @param <E> is the type of the elements a {@link IndexRequestable}.
 */
public interface IndexRequestable<E> {
  /**
   * @param selector
   * @return the 1-based index of the first element the given selector selects
   *         from the current {@link IndexRequestable}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain an element the given selector selects.
   */
  int getOneBasedIndexOfFirst(Predicate<E> selector);

  /**
   * @param object
   * @return the 1-based index of the first element of the current
   *         {@link IndexRequestable} that equals the given object.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain an element that equals the given object.
   */
  int getOneBasedIndexOfFirstEqualElement(Object object);

  /**
   * @param object
   * @return the 1-based index of the first occurrence of the given object in the
   *         current {@link IndexRequestable}.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain the given object.
   */
  int getOneBasedIndexOfFirstOccurrenceOf(Object object);
}
