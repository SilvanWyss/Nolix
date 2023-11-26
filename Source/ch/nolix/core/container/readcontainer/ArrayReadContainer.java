//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.container.arraycontrol.ArrayIterator;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypeapi.stringutilapi.CharacterCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-26
 * @param <E> is the type of the elements of a {@link ArrayReadContainer}.
 */
public final class ArrayReadContainer<E> extends Container<E> {

  //attribute
  private final E[] array;

  //constructor
  /**
   * Creates a new {@link ArrayReadContainer} for a new empty array.
   */
  @SuppressWarnings("unchecked")
  public ArrayReadContainer() {

    //Calls other constructor.
    this((E[]) new Object[0]);
  }

  //constructor
  /**
   * Creates a new {@link ArrayReadContainer} for the given array.
   * 
   * @param array
   * @throws ArgumentIsNullException if the given array is null.
   */
  private ArrayReadContainer(final E[] array) {

    //Asserts that the given array is not null.
    GlobalValidator
      .assertThat(array)
      .thatIsNamed(LowerCaseCatalogue.ARRAY)
      .isNotNull();

    //Sets the array of the current ArrayReadContainer.
    this.array = array; //NOSONAR: An ArrayReadContainer operates on the original instance.
  }

  //static method
  /**
   * @param array
   * @param <E2>  is the type of the elements of the given array.
   * @return a new {@link ArrayReadContainer} for the given array.
   * @throws ArgumentIsNullException if the given array is null.
   */
  public static <E2> ArrayReadContainer<E2> forArray(final E2[] array) {
    return new ArrayReadContainer<>(array);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getElementCount() {
    return array.length;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {

    GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
    GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseCatalogue.INDEX).isNotBiggerThan(getElementCount());

    return array[p1BasedIndex - 1];
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return array[getElementCount() - 1];
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(array);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  //method
  /**
   * The complexity of this implementation is O(n). if the current
   * {@link ArrayReadContainer} contains n elements.
   * 
   * @return a {@link String} representation of the current
   *         {@link ArrayReadContainer}.
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }
}
