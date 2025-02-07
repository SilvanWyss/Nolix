package ch.nolix.core.independent.iterabletool;

import ch.nolix.coreapi.independentapi.iterabletoolapi.IIterableTool;

/**
 * @author Silvan Wyss
 * @version 2017-12-16
 */
public final class IterableTool implements IIterableTool {

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
  public int getElementCount(final Iterable<?> iterable) {

    if (iterable == null) {
      return 0;
    }

    return getElementCountWhenIsNotNull(iterable);
  }
}
