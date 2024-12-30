package ch.nolix.core.commontypetool.iteratortool;

import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;

public final class IterableTool implements IIterableTool {

  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

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
  public boolean isEmpty(final Iterable<?> iterable) {
    return //
    iterable == null
    || !iterable.iterator().hasNext();
  }
}
