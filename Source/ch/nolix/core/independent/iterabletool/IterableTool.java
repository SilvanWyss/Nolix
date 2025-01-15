package ch.nolix.core.independent.iterabletool;

import ch.nolix.coreapi.independentapi.iterabletoolapi.IIterableTool;

/**
 * @author Silvan Wyss
 * @version 2017-12-16
 */
public final class IterableTool implements IIterableTool {

  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element multiple times,
   *         false otherwise, for the case that the given iterable is not null.
   */
  private static boolean containsElementMultipleTimesWhenIsNotNull(final Iterable<?> iterable, final Object element) {

    var found = false;

    for (final var e : iterable) {
      if (e == element) {

        if (found) {
          return true;
        }

        found = true;
      }
    }

    return false;
  }

  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element exactly 1 time,
   *         false otherwise, for the case that the given iterable is not null.
   */
  private static boolean containsElementOnceWhenIsNotNull(final Iterable<?> iterable, final Object element) {

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

  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element, false
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

  /**
   * @param iterable
   * @param stringRepresentation
   * @return true if the given iterable contains exactly 1 element with the given
   *         stringRepresentation, false otherwise, for the case that the given
   *         iterable is not null.
   */
  private static boolean containsExacltyOneWithStringRepresentationWhenIsNotNull(
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

  /**
   * @param iterable
   * @return the number of elements of the given iterable for the case that the
   *         given iterable is not null.
   */
  private static int getElementCountWhenIsNotNull(final Iterable<?> iterable) {

    var elementCount = 0;

    final var iterator = iterable.iterator();

    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsElement(final Iterable<?> iterable, final Object element) {

    if (iterable == null) {
      return false;
    }

    return containsElementWhenIsNotNull(iterable, element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsElementMultipleTimes(final Iterable<?> iterable, final Object element) {
    return //
    iterable != null
    && containsElementMultipleTimesWhenIsNotNull(iterable, element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsElementOnce(final Iterable<?> iterable, final Object element) {

    if (iterable == null) {
      return false;
    }

    return containsElementOnceWhenIsNotNull(iterable, element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsExactlyOneWithStringRepresentation(
    final Iterable<?> iterable,
    final String stringRepresentation) {

    if (iterable == null) {
      return false;
    }

    return containsExacltyOneWithStringRepresentationWhenIsNotNull(iterable, stringRepresentation);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getElementCount(final Iterable<?> iterable) {

    if (iterable == null) {
      return 0;
    }

    return getElementCountWhenIsNotNull(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty(final Iterable<?> iterable) {

    if (iterable == null) {
      throw new IllegalArgumentException("The given iterable is null.");
    }

    return !iterable.iterator().hasNext();
  }
}
