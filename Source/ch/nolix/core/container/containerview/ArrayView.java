package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2017-11-26
 * @param <E> is the type of the elements of a {@link ArrayView}.
 */
public final class ArrayView<E> extends Container<E> {

  private final E[] array;

  /**
   * Creates a new {@link ArrayView} for a new empty array.
   */
  @SuppressWarnings("unchecked")
  public ArrayView() {

    //Calls other constructor.
    this((E[]) new Object[0]);
  }

  /**
   * Creates a new {@link ArrayView} for the given array.
   * 
   * @param array
   * @throws ArgumentIsNullException if the given array is null.
   */
  private ArrayView(final E[] array) {

    //Asserts that the given array is not null.
    GlobalValidator
      .assertThat(array)
      .thatIsNamed(LowerCaseVariableCatalogue.ARRAY)
      .isNotNull();

    //Sets the array of the current ArrayView.
    this.array = array; //NOSONAR: An ArrayView operates on the original instance.
  }

  /**
   * @param array
   * @param <E2>  is the type of the elements of the given array.
   * @return a new {@link ArrayView} for the given array.
   * @throws ArgumentIsNullException if the given array is null.
   */
  public static <E2> ArrayView<E2> forArray(final E2[] array) {
    return new ArrayView<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return array.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {

    GlobalValidator.assertThat(param1BasedIndex).thatIsNamed(LowerCaseVariableCatalogue.INDEX).isPositive();

    GlobalValidator
      .assertThat(param1BasedIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.INDEX)
      .isNotBiggerThan(getCount());

    return array[param1BasedIndex - 1];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  /**
   * @return a {@link String} representation of the current {@link ArrayView}.
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
