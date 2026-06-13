/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.iteratortool;

import ch.nolix.base.commontypetool.iterableexaminer.IterableExaminer;
import ch.nolix.baseapi.commontypetool.iterabletool.IIterableTool;

/**
 * @author Silvan Wyss
 */
public final class IterableTool {
  private static final IterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

  private static final IIterableTool ITERABLE_TOOL = new IterableToolUnit();

  private IterableTool() {
  }

  public static boolean containsAny(final Iterable<?> iterable) {
    return ITERABLE_EXAMINER.containsAny(iterable);
  }

  public static int getCount(final Iterable<?> iterable) {
    return ITERABLE_TOOL.getCount(iterable);
  }

  public static <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, final int oneBasedIndex) {
    return ITERABLE_TOOL.getStoredAtOneBasedIndex(iterable, oneBasedIndex);
  }

  public static boolean isEmpty(final Iterable<?> iterable) {
    return ITERABLE_EXAMINER.isEmpty(iterable);
  }
}
