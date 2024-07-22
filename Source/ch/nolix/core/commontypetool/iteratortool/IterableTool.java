//package declaration
package ch.nolix.core.commontypetool.iteratortool;

//Java imports
import java.util.Objects;

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
  public boolean containsEqualing(final Iterable<?> iterable, final Object object) {
    return //
    iterable != null
    && containsEqualingWhenIsNotNull(iterable, object);
  }

  //method
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

  //method
  private boolean containsEqualingWhenIsNotNull(final Iterable<?> iterable, final Object object) {

    for (final var e : iterable) {
      if (Objects.equals(e, object)) {
        return true;
      }
    }

    return false;
  }
}
