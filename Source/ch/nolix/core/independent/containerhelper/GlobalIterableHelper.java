//package declaration
package ch.nolix.core.independent.containerhelper;

//Java imports
import java.util.Objects;

//class
/**
 * The {@link GlobalIterableHelper} provides methods to handle
 * {@link Iterable}s. Of the {@link GlobalIterableHelper} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @date 2017-12-16
 */
public final class GlobalIterableHelper {

  // static method
  /**
   * @param iterable
   * @param element
   * @return true if the given container contains the given element, false
   *         otherwise.
   */
  public static boolean containsElement(final Iterable<?> iterable, final Object element) {

    if (iterable == null) {
      return false;
    }

    return containsElementWhenIsNotNull(iterable, element);
  }

  // static method
  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element exactly 1 time,
   *         false otherwise.
   */
  public static boolean containsElementOnce(final Iterable<?> iterable, final Object element) {

    var found = false;

    for (final var e : iterable) {
      if (e == element) {

        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  // static method
  /**
   * @param container
   * @param object
   * @return true if the given container contains an element that equals the given
   *         object.
   */
  public static boolean containsEqualing(final Iterable<?> container, final Object object) {

    for (final var e : container) {
      if (Objects.equals(e, object)) {
        return true;
      }
    }

    return false;
  }

  // static method
  /**
   * @param container
   * @return the number of elements of the given container.
   */
  public static int getElementCount(final Iterable<?> container) {

    var elementCount = 0;
    final var iterator = container.iterator();
    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }

  // static method
  /**
   * @param container
   * @return true if the given container is not empty.
   */
  public static boolean isEmpty(final Iterable<?> container) {
    return !container.iterator().hasNext();
  }

  // static method
  /**
   * @param iterable
   * @param element
   * @return true if the given container contains the given element, false
   *         otherwise, for the case that the given iterable is not null.
   */
  private static boolean containsElementWhenIsNotNull(final Iterable<?> iterable, final Object element) {

    for (final var e : iterable) {
      if (e == element) {
        return true;
      }
    }

    return false;
  }

  // constructor
  /**
   * Prevents that an instance of the {@link GlobalIterableHelper} can be created.
   */
  private GlobalIterableHelper() {
  }
}
