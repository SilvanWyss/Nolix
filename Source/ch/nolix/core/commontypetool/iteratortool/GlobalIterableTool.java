//package declaration
package ch.nolix.core.commontypetool.iteratortool;

//Java imports
import java.util.Objects;

//class
public final class GlobalIterableTool {

  //constructor
  private GlobalIterableTool() {
  }

  //static method
  public static boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

  //static method
  public static boolean containsEqualing(final Iterable<?> iterable, final Object object) {
    return //
    iterable != null
    && containsEqualingWhenIsNotNull(iterable, object);
  }

  //static method
  public static int getElementCount(final Iterable<?> iterable) {

    final var iterator = iterable.iterator();
    var elementCount = 0;

    while (iterator.hasNext()) {
      elementCount++;
      iterator.next();
    }

    return elementCount;
  }

  //static method
  private static boolean containsEqualingWhenIsNotNull(final Iterable<?> iterable, final Object object) {

    for (final var e : iterable) {
      if (Objects.equals(e, object)) {
        return true;
      }
    }

    return false;
  }
}
