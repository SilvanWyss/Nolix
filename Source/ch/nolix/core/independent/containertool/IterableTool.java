//package declaration
package ch.nolix.core.independent.containertool;

//Java imports
import java.util.Objects;

//class
/**
 * The {@link IterableTool} provides methods to handle {@link Iterable}s.
 * 
 * @author Silvan Wyss
 * @version 2017-12-16
 */
public final class IterableTool {

  //method
  /**
   * @param iterable
   * @param element
   * @return true if the given container contains the given element, false
   *         otherwise.
   */
  public boolean containsElement(final Iterable<?> iterable, final Object element) {

    if (iterable == null) {
      return false;
    }

    return containsElementWhenIsNotNull(iterable, element);
  }

  //method
  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element exactly 1 time,
   *         false otherwise.
   */
  public boolean containsElementOnce(final Iterable<?> iterable, final Object element) {

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

  //method
  /**
   * @param container
   * @param object
   * @return true if the given container contains an element that equals the given
   *         object.
   */
  public boolean containsEqualing(final Iterable<?> container, final Object object) {

    for (final var e : container) {
      if (Objects.equals(e, object)) {
        return true;
      }
    }

    return false;
  }

  //method
  /**
   * @param iterable
   * @param stringRepresentation
   * @return true if the given iterable contains exactly 1 element with the given
   *         stringRepresentation
   */
  public boolean containsExactlyOneWithStringRepresentation(
    final Iterable<?> iterable,
    final String stringRepresentation) {
    return //
    iterable != null
    && containsExacltyOneWithStringRepresentationWhenIsNotNull(iterable, stringRepresentation);
  }

  //method
  /**
   * @param container
   * @return the number of elements of the given container.
   */
  public int getElementCount(final Iterable<?> container) {

    var elementCount = 0;
    final var iterator = container.iterator();
    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }

  //method
  /**
   * @param container
   * @return true if the given container is not empty.
   */
  public boolean isEmpty(final Iterable<?> container) {
    return !container.iterator().hasNext();
  }

  //method
  /**
   * @param iterable
   * @param element
   * @return true if the given container contains the given element, false
   *         otherwise, for the case that the given iterable is not null.
   */
  private boolean containsElementWhenIsNotNull(final Iterable<?> iterable, final Object element) {

    for (final var e : iterable) {
      if (e == element) {
        return true;
      }
    }

    return false;
  }

  //method
  private boolean containsExacltyOneWithStringRepresentationWhenIsNotNull(
    final Iterable<?> iterable,
    final String stringRepresentation) {

    var found = false;

    for (final var e : iterable) {
      if (e != null && e.toString().equals(stringRepresentation)) {

        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }
}
