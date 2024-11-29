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
   *         object, false otherwise.
   */
  boolean contains(Object object);

  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object and all of the given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object object, Object... objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object[] objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Iterable<?> objects);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains an element
   *         the given selector selects, false otherwise. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsAny(Predicate<E> selector);

  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object or one of the given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAny(Object object, Object... objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAnyOf(Iterable<?> objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAnyOf(Object[] objects);

  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains as many
   *         elements as the given container, false otherwise.
   */
  boolean containsAsManyAs(Iterable<?> container);

  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains an element
   *         that equals the given given element, false otherwise.
   */
  boolean containsEqualing(Object element);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order,
   *         false otherwise.
   */
  boolean containsExactlyEqualingInSameOrder(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly the
   *         elements of the given iterable in the same order, false otherwise.
   */
  boolean containsExactlyInSameOrder(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains less elements
   *         than the given container, false otherwise
   */
  boolean containsLessThan(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains more elements
   *         than the given container, false otherwise.
   */
  boolean containsMoreThan(Iterable<?> iterable);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} does not contain an
   *         element the given selector selects. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsNone(Predicate<E> selector);

  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} does not contain the
   *         given object and none of the given objects, false otherwise.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsNone(Object object, Object... objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} does not contain any
   *         of the given objects.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsNoneOf(Iterable<?> objects);

  /**
   * @param object
   * @return true if the current {@link StoringRequestable} contains the given
   *         object exactly 1 time, false otherwise.
   */
  boolean containsOnce(Object object);

  /**
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element, false otherwise.
   */
  boolean containsOne();

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element the given selector selects, false otherwise. Ignores null
   *         elements.
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsOne(Predicate<E> selector);

  /**
   * @param object
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element that equals the given object, false otherwise.
   */
  boolean containsOneEqualing(E object);

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains only elements
   *         the given selector selects. Null element are regarded as
   *         unselectable.
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsOnly(Predicate<E> selector);
}
