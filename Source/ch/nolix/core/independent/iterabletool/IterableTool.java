/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.independent.iterabletool;

import ch.nolix.coreapi.independent.iterabletool.IIterableTool;

/**
 * @author Silvan Wyss
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
