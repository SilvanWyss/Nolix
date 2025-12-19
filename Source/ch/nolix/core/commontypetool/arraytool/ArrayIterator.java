package ch.nolix.core.commontypetool.arraytool;

import java.util.NoSuchElementException;

import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

/**
 * @author Silvan Wyss
 */
public final class ArrayIterator<E> implements CopyableIterator<E> {
  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  private final E[] parentArray;

  private int nextIndex;

  private ArrayIterator(final E[] parrentArray) {
    Validator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  private ArrayIterator(final E[] parrentArray, final int startIndex) {
    Validator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    Validator.assertThat(startIndex).thatIsNamed("start index").isNotNegative();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = startIndex;
  }

  public static <T> ArrayIterator<T> forArray(final T[] array) {
    return new ArrayIterator<>(array);
  }

  public static <T> ArrayIterator<T> forArrayAndStartIndex(final T[] array, final int startIndex) {
    return new ArrayIterator<>(array, startIndex);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return forArrayAndStartIndex(parentArray, nextIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextIndex < parentArray.length);
  }

  @Override
  public E next() {
    assertHasNext();

    return nextWhenHasNext();
  }

  private void assertHasNext() throws NoSuchElementException {
    ITERATOR_VALIDATOR.assertHasNext(this);
  }

  private E nextWhenHasNext() {
    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
