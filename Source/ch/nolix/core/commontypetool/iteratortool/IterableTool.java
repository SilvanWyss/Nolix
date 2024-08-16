//package declaration
package ch.nolix.core.commontypetool.iteratortool;

//own imports
import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;

//class
public final class IterableTool implements IIterableTool {

  //method
  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

  //method
  @Override
  public int getCount(final Iterable<?> iterable) {

    if (iterable != null) {
      return getCountWhenIsNotNull(iterable);
    }

    return 0;
  }

  //method
  @Override
  public boolean isEmpty(final Iterable<?> iterable) {
    return //
    iterable == null
    || !iterable.iterator().hasNext();
  }

  //method
  private int getCountWhenIsNotNull(final Iterable<?> iterable) {

    final var iterator = iterable.iterator();
    var elementCount = 0;

    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }
}
