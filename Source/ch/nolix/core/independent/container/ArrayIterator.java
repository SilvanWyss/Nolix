//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//class
public final class ArrayIterator<E> implements Iterator<E> {

  // static method
  public static <E2> ArrayIterator<E2> forArray(final E2[] array) {
    return new ArrayIterator<>(array);
  }

  // attribute
  private final E[] parentArray;

  // attribute
  private int nextIndex;

  // constructor
  private ArrayIterator(final E[] parrentArray) {

    if (parrentArray == null) {
      throw new IllegalArgumentException("The given parent array is null.");
    }

    this.parentArray = parrentArray; // NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  // method
  @Override
  public boolean hasNext() {
    return (nextIndex < parentArray.length);
  }

  // method
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  // method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("The current ArrayIterator does not have a next element.");
    }
  }

  // method
  private E nextWhenHasNext() {

    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
