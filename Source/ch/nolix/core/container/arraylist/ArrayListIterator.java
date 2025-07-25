package ch.nolix.core.container.arraylist;

import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

public final class ArrayListIterator<E> implements CopyableIterator<E> {

  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  private final E[] parentArray;

  private final int maxNextIndex;

  private int nextIndex;

  private ArrayListIterator(final E[] parrentArray, final int maxNextIndex) {

    Validator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    Validator.assertThat(maxNextIndex).thatIsNamed("max next index").isNotNegative();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    this.maxNextIndex = maxNextIndex;
    nextIndex = 0;
  }

  private ArrayListIterator(final E[] parrentArray, final int startIndex, final int maxNexIndex) {

    Validator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    Validator.assertThat(startIndex).thatIsNamed("start index").isNotNegative();
    Validator.assertThat(maxNexIndex).thatIsNamed("max next index").isBiggerThanOrEquals(startIndex);

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    this.maxNextIndex = maxNexIndex;
    nextIndex = startIndex;
  }

  public static <E2> ArrayListIterator<E2> forArrayAndMaxNextIndex(final E2[] array, final int maxNextIndex) {
    return new ArrayListIterator<>(array, maxNextIndex);
  }

  public static <E2> ArrayListIterator<E2> forArrayAndStartIndexAndMaxNextIndex(
    final E2[] array,
    final int startIndex,
    final int maxIndex) {
    return new ArrayListIterator<>(array, startIndex, maxIndex);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return forArrayAndStartIndexAndMaxNextIndex(parentArray, nextIndex, maxNextIndex);
  }

  @Override
  public boolean hasNext() {
    return (nextIndex < maxNextIndex);
  }

  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  private void assertHasNext() {
    ITERATOR_VALIDATOR.assertHasNext(this);
  }

  private E nextWhenHasNext() {

    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
