/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.iteratortool;

import ch.nolix.baseapi.commontypetool.iterabletool.IIterableTool;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;

/**
 * @author Silvan Wyss
 */
public final class IterableToolUnit implements IIterableTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, final int oneBasedIndex) {
    var iteratingOneBasedIndex = 1;

    for (final var e : iterable) {
      if (iteratingOneBasedIndex == oneBasedIndex) {
        return e;
      }

      iteratingOneBasedIndex++;
    }

    final var count = iteratingOneBasedIndex - 1;

    throw //
    ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
      1,
      count);
  }
}
