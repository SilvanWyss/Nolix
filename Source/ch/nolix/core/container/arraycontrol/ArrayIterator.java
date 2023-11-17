//package declaration
package ch.nolix.core.container.arraycontrol;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public final class ArrayIterator<E> implements CopyableIterator<E> {

  //attribute
  private final E[] parentArray;

  //attribute
  private int nextIndex;

  //constructor
  private ArrayIterator(final E[] parrentArray) {

    GlobalValidator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  //constructor
  private ArrayIterator(final E[] parrentArray, final int startIndex) {

    GlobalValidator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    GlobalValidator.assertThat(startIndex).thatIsNamed("start index").isNotNegative();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = startIndex;
  }

  //static method
  public static <E2> ArrayIterator<E2> forArray(final E2[] array) {
    return new ArrayIterator<>(array);
  }

  //static method
  public static <E2> ArrayIterator<E2> forArrayAndStartIndex(final E2[] array, final int startIndex) {
    return new ArrayIterator<>(array, startIndex);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return forArrayAndStartIndex(parentArray, nextIndex);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextIndex < parentArray.length);
  }

  //method
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
