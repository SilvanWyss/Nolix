package ch.nolix.core.commontypetool.iteratortool;

import ch.nolix.coreapi.commontypetool.iterabletool.IIterableExaminer;
import ch.nolix.coreapi.commontypetool.iterabletool.IIterableTool;

public final class IterableTool {

  private static final IIterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

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
