//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.util.function.Predicate;

//interface
/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link StoringRequestable}.
 */
public interface StoringRequestable<E> {

  //method declaration
  /**
   * @param object
   * @return true if the current {@link StoringRequestable} contains the given
   *         object.
   */
  boolean contains(Object object);

  //method declaration
  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object and all of the given objects.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object object, Object... objects);

  //method declaration
  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects.
   * @throws RuntimeException if the given objects is null.
   */
  boolean containsAll(Object[] objects);

  //method declaration
  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects.
   */
  boolean containsAll(Iterable<?> objects);

  //method declaration
  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains an element
   *         the given selector selects.
   */
  boolean containsAny(Predicate<E> selector);

  //method declaration
  /**
   * @param object
   * @param objects
   * @return true if the current {@link StoringRequestable} contains the given
   *         object or one of the given objects.
   */
  boolean containsAny(Object object, Object... objects);

  //method declaration
  /**
   * @param elements
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects.
   */
  boolean containsAnyOf(Iterable<?> elements);

  //method declaration
  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects.
   */
  boolean containsAnyOf(Object[] objects);

  //method declaration
  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains as many
   *         elements as the given container.
   */
  boolean containsAsManyAs(Iterable<?> container);

  //method declaration
  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains an element
   *         that equals the given given element.
   */
  boolean containsEqualing(Object element);

  //method declaration
  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order
   *         like the given iterable, false otherwise.
   */
  boolean containsExactlyEqualingInSameOrder(Iterable<?> iterable);

  //method declaration
  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly the
   *         elements of the given iterable in the same order like the given
   *         iterable, false otherwise.
   */
  boolean containsExactlyInSameOrder(Iterable<?> iterable);

  //method declaration
  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains less elements
   *         than the given container.
   */
  boolean containsLessThan(Iterable<?> container);

  //method declaration
  /**
   * @param container
   * @return true if the current {@link StoringRequestable} contains more elements
   *         than the given container.
   */
  boolean containsMoreThan(Iterable<?> container);

  //method declaration
  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} does not contain an
   *         element the given selector selects.
   */
  boolean containsNone(Predicate<E> selector);

  //method declaration
  /**
   * @param element
   * @param elements
   * @return true if the current {@link StoringRequestable} does not contain the
   *         given element and none of the given elements.
   */
  boolean containsNone(Object element, Object... elements);

  //method declaration
  /**
   * @param elements
   * @return true if the current {@link StoringRequestable} does not contain any
   *         element of the given elements.
   */
  boolean containsNoneOf(Iterable<?> elements);

  //method declaration
  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains the given
   *         element exactly 1 time, false otherwise.
   */
  boolean containsOnce(Object element);

  //method declaration
  /**
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element.
   */
  boolean containsOne();

  //method declaration
  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element the given selector selects.
   */
  boolean containsOne(Predicate<E> selector);

  //method declaration
  /**
   * @param element
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element that equals the given element.
   */
  boolean containsOneEqualing(E element);

  //method declaration
  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains only elements
   *         the given selector selects.
   */
  boolean containsOnly(Predicate<E> selector);
}
