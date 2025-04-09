package ch.nolix.core.commontypetool.iteratortool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;

public final class IterableTool implements IIterableTool {

  @Override
  public int getCount(final Iterable<?> iterable) {

    final var iterator = iterable.iterator();
    var elementCount = 0;

    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }

  @Override
  public <E> E getStoredAt1BasedIndex(final Iterable<E> iterable, final int oneBasedIndex) {

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
