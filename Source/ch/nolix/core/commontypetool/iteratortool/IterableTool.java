package ch.nolix.core.commontypetool.iteratortool;

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
}
