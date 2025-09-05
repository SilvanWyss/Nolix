package ch.nolix.core.commontypetool.iteratortool;

import ch.nolix.coreapi.commontypetool.iterabletool.IIterableExaminer;

public final class IterableExaminer implements IIterableExaminer {
  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

  @Override
  public boolean isEmpty(final Iterable<?> iterable) {
    return //
    iterable == null
    || !iterable.iterator().hasNext();
  }
}
