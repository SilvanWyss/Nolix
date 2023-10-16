//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Iterator;

//class
public final class ArrayReadContainer<E> implements Iterable<E> {

  //attribute
  private final E[] array;

  //constructor
  public ArrayReadContainer(final E[] array) {

    //Asserts that the given array is not null.
    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    //Sets the array of the current array read container.
    this.array = array; //NOSONAR: An ArrayReadContainer operates on the original instance.
  }

  //method
  public int getSize() {
    return array.length;
  }

  //method
  @Override
  public Iterator<E> iterator() {
    return ArrayIterator.forArray(array);
  }
}
