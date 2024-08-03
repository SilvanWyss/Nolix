//package declaration
package ch.nolix.core.commontypetool.iteratortool;

//Java imports
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

//class
public final class IterableTool implements IIterableTool {

  //method
  @Override
  public boolean containsAllObjects(final Iterable<?> iterable, final Object[] objects) {
    return //
    iterable != null
    && containsAllObjectsWhenIsNotNull(iterable, objects);
  }

  //method
  @Override
  public boolean containsAllObjects(final Iterable<?> iterable, final Object object, final Object... objects) {
    return //
    iterable != null
    && containsObject(iterable, object)
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
  public boolean containsObject(final Iterable<?> iterable, final Object object, final Object... objects) {
    return //
    iterable != null
    && containsObjectWhenIsNotNull(iterable, object, objects);
  }

  //method
  @Override
  public boolean containsObjectOnce(final Iterable<?> iterable, final Object object) {
    return //
    iterable != null
    && containsObjectOnceWhenIsNotNull(iterable, object);
  }

  //method
  @Override
  public int get1BasedIndexOfFirstEqualElement(final Iterable<?> iterable, final Object object) {

    if (iterable == null) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        Iterable.class.getSimpleName(),
        iterable,
        "does not contain an equal element");
    }

    return get1BasedIndexOfFirstEqualElementWhenIsNotNull(iterable, object);
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
  private boolean containsObjectOnceWhenIsNotNull(final Iterable<?> iterable, final Object object) {

    var found = false;

    //Iterates the given iterable.
    for (final var e : iterable) {

      //Handles the case that the current element is the given object.
      if (e == object) {

        //Handles the case that the given element is already found.
        if (found) {
          return false;
        }

        //Handles the case that the given element is not already found.
        found = true;
      }
    }

    return found;
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

  //method
  private boolean containsObjectWhenIsNotNull(
    final Iterable<?> iterable,
    final Object object,
    final Object... objects) {

    GlobalValidator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalogue.OBJECTS).isNotNull();

    if (!containsObjectWhenIsNotNull(iterable, object)) {
      return false;
    }

    for (final var o : objects) {
      if (!containsObjectWhenIsNotNull(iterable, o)) {
        return false;
      }
    }

    return true;
  }

  //method
  private int get1BasedIndexOfFirstEqualElementWhenIsNotNull(final Iterable<?> iterable, final Object object) {

    var index = 1;

    for (final var e : iterable) {

      if (Objects.equals(e, object)) {
        return index;
      }

      index++;
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(iterable, "does not contain an equal element");
  }
}
