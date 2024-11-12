package ch.nolix.coreapi.containerapi.commoncontainerapi;

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
   *         from the current {@link IndexRequestable}.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain an element the given selector selects.
   */
  int get1BasedIndexOfFirst(Predicate<E> selector);

  /**
   * @param element
   * @return the 1-based index of the first element of the current
   *         {@link IndexRequestable} that equals the given element.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain an element that equals the given element.
   */
  int get1BasedIndexOfFirstEqualElement(E element);

  /**
   * @param element
   * @return the 1-based index of the first occurrence of the given element in the
   *         current {@link IndexRequestable}.
   * @throws RuntimeException if the current {@link IndexRequestable} does not
   *                          contain the given element.
   */
  int get1BasedIndexOfFirstOccurrenceOf(E element);
}
