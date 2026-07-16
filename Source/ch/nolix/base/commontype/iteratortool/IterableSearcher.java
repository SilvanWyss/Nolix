/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.iteratortool;

import java.util.function.Predicate;

import ch.nolix.baseapi.commontype.iterabletool.IIterableSearcher;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

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
  public <E> int getCount(final Iterable<E> iterable, final Predicate<E> selector) {
    if (iterable != null && selector != null) {
      var count = 0;

      for (final var e : iterable) {
        if (e != null && selector.test(e)) {
          count++;
        }
      }

      return count;
    }

    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCountOf(final Iterable<?> iterable, final Object object) {
    if (iterable != null) {
      var count = 0;

      for (final var e : iterable) {
        if (e == object) {
          count++;
        }
      }

      return count;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> E getStoredFirstNonNull(final Iterable<E> iterable) {
    if (iterable != null) {
      final var iterator = iterable.iterator();

      while (iterator.hasNext()) {
        final var element = iterator.next();

        if (element != null) {
          return element;
        }
      }
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(iterable, "does not contain a non-null element");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> E getStoredFirst(final Iterable<E> iterable, final Predicate<? super E> selector) {
    if (selector == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.SELECTOR);
    }

    if (iterable != null) {
      for (final var e : iterable) {
        if (e != null && selector.test(e)) {
          return e;
        }
      }
    }

    throw //
    InvalidArgumentException.forArgumentAndErrorPredicate(
      iterable,
      "does not contain a non-null element the given selector selects");
  }
}
