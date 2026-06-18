/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iteratortool;

import ch.nolix.baseapi.commontype.iterabletool.IIterableSearcher;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;

/**
 * @author Silvan Wyss
 */
public final class IterableSearcher implements IIterableSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount(final Iterable<?> iterable) {
    if (iterable != null) {
      var elementCount = 0;
      final var iterator = iterable.iterator();

      while (iterator.hasNext()) {
        elementCount++;
        iterator.next();
      }

      return elementCount;
    }

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, final int oneBasedIndex) {
    var iteratorOneBasedIndex = 1;

    for (final var e : iterable) {
      if (iteratorOneBasedIndex == oneBasedIndex) {
        return e;
      }

      iteratorOneBasedIndex++;
    }

    final var count = iteratorOneBasedIndex - 1;

    throw //
    ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
      1,
      count);
  }
}
