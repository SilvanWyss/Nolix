/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.iteratortool;

import ch.nolix.base.commontypeexaminer.iterableexaminer.IterableExaminer;

/**
 * @author Silvan Wyss
 */
public final class IterableTool {
  private static final IterableSearcher ITERABLE_SEARCHER = new IterableSearcher();

  private static final IterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

  private IterableTool() {
  }

  public static boolean containsAny(final Iterable<?> iterable) {
    return ITERABLE_EXAMINER.containsAny(iterable);
  }

  public static int getCount(final Iterable<?> iterable) {
    return ITERABLE_SEARCHER.getCount(iterable);
  }

  public static <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, final int oneBasedIndex) {
    return ITERABLE_SEARCHER.getStoredAtOneBasedIndex(iterable, oneBasedIndex);
  }

  public static boolean isEmpty(final Iterable<?> iterable) {
    return ITERABLE_EXAMINER.isEmpty(iterable);
  }
}
