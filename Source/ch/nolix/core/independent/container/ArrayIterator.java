package ch.nolix.core.independent.container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class ArrayIterator<E> implements Iterator<E> {

  private final E[] parentArray;

  private int nextIndex;

  private ArrayIterator(final E[] parrentArray) {

    if (parrentArray == null) {
      throw new IllegalArgumentException("The given parent array is null.");
    }

    this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  public static <E2> ArrayIterator<E2> forArray(final E2[] array) {
    return new ArrayIterator<>(array);
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
    if (!hasNext()) {
      throw new NoSuchElementException("The current ArrayIterator does not have a next element.");
    }
  }

  private E nextWhenHasNext() {

    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
