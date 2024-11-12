package ch.nolix.coreapi.containerapi.commoncontainerapi;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link StoringRequestable}.
 */
public interface StoringRequestable<E> {

  /**
   * @param object
   * @return true if the current {@link StoringRequestable} contains the given
   *         object.
   */
  boolean contains(Object object);

  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object and all of the given objects.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object object, Object... objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object[] objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects.
   */
  boolean containsAll(Iterable<?> objects);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains an element
   *         the given selector selects.
   */
  boolean containsAny(Predicate<E> selector);

  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object or one of the given objects.
   */
  boolean containsAny(Object object, Object... objects);

  /**
   * @param elements
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects.
   */
  boolean containsAnyOf(Iterable<?> elements);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects.
   */
  boolean containsAnyOf(Object[] objects);

  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains as many
   *         elements as the given container.
   */
  boolean containsAsManyAs(Iterable<?> container);

  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains an element
   *         that equals the given given element.
   */
  boolean containsEqualing(Object element);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order
   *         like the given iterable, false otherwise.
   */
  boolean containsExactlyEqualingInSameOrder(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly the
   *         elements of the given iterable in the same order like the given
   *         iterable, false otherwise.
   */
  boolean containsExactlyInSameOrder(Iterable<?> iterable);

  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains less elements
   *         than the given container.
   */
  boolean containsLessThan(Iterable<?> container);

  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains more elements
   *         than the given container.
   */
  boolean containsMoreThan(Iterable<?> container);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} does not contain an
   *         element the given selector selects.
   */
  boolean containsNone(Predicate<E> selector);

  /**
   * @param element
   * @param elements
   * @return true if the current {@link StoringRequestable} does not contain the
   *         given element and none of the given elements.
   */
  boolean containsNone(Object element, Object... elements);

  /**
   * @param elements
   * @return true if the current {@link StoringRequestable} does not contain any
   *         element of the given elements.
   */
  boolean containsNoneOf(Iterable<?> elements);

  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains the given
   *         element exactly 1 time, false otherwise.
   */
  boolean containsOnce(Object element);

  /**
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element.
   */
  boolean containsOne();

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element the given selector selects.
   */
  boolean containsOne(Predicate<E> selector);

  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element that equals the given element.
   */
  boolean containsOneEqualing(E element);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains only elements
   *         the given selector selects.
   */
  boolean containsOnly(Predicate<E> selector);
}
