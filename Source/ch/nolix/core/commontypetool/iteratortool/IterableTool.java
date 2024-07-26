//package declaration
package ch.nolix.core.commontypetool.iteratortool;

//Java imports
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

//class
public final class IterableTool implements IIterableTool {

  //method
  @Override
  public boolean containsAllObjects(final Iterable<?> iterable, final Object[] objects) {
    return iterable != null
    && containsAllObjectsWhenIsNotNull(iterable, objects);
  }

  //method
  @Override
  public boolean containsAny(final Iterable<?> iterable) {
    return //
    iterable != null
    && iterable.iterator().hasNext();
  }

  //method
  @Override
  public boolean containsAnyThatEqualsTheObject(final Iterable<?> iterable, final Object object) {
    return //
    iterable != null
    && containsEqualingWhenIsNotNull(iterable, object);
  }

  //method
  @Override
  public boolean containsObject(final Iterable<?> iterable, final Object object) {
    return //
    iterable != null
    && containsObjectWhenIsNotNull(iterable, object);
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
  private boolean containsAllObjectsWhenIsNotNull(final Iterable<?> iterable, final Object[] objects) {

    GlobalValidator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalogue.OBJECTS).isNotNull();

    for (final var object : objects) {
      if (!containsObject(iterable, object)) {
        return false;
      }
    }

    return true;
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

  //method
  private boolean containsObjectWhenIsNotNull(final Iterable<?> iterable, final Object object) {

    for (final var e : iterable) {
      if (e == object) {
        return true;
      }
    }

    return false;
  }
}
