//package declaration
package ch.nolix.core.container.arraylist;

//own imports
import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

//class
public final class ArrayListIterator<E> implements CopyableIterator<E> {

  //constant
  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  //attribute
  private final E[] parentArray;

  //attribute
  private final int maxNextIndex;

  //attribute
  private int nextIndex;

  //constructor
  private ArrayListIterator(final E[] parrentArray, final int maxNextIndex) {

    GlobalValidator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    GlobalValidator.assertThat(maxNextIndex).thatIsNamed("max next index").isNotNegative();

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    this.maxNextIndex = maxNextIndex;
    nextIndex = 0;
  }

  //constructor
  private ArrayListIterator(final E[] parrentArray, final int startIndex, final int maxNexIndex) {

    GlobalValidator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
    GlobalValidator.assertThat(startIndex).thatIsNamed("start index").isNotNegative();
    GlobalValidator.assertThat(maxNexIndex).thatIsNamed("max next index").isBiggerThanOrEquals(startIndex);

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    this.maxNextIndex = maxNexIndex;
    nextIndex = startIndex;
  }

  //static method
  public static <E2> ArrayListIterator<E2> forArrayAndMaxNextIndex(final E2[] array, final int maxNextIndex) {
    return new ArrayListIterator<>(array, maxNextIndex);
  }

  //static method
  public static <E2> ArrayListIterator<E2> forArrayAndStartIndexAndMaxNextIndex(
    final E2[] array,
    final int startIndex,
    final int maxIndex) {
    return new ArrayListIterator<>(array, startIndex, maxIndex);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return forArrayAndStartIndexAndMaxNextIndex(parentArray, nextIndex, maxNextIndex);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextIndex < maxNextIndex);
  }

  //method
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() {
    ITERATOR_VALIDATOR.assertHasNext(this);
  }

  //method
  private E nextWhenHasNext() {

    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
