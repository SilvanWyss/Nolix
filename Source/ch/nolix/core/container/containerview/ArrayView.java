package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-11-26
 * @param <E> is the type of the elements of a {@link ArrayView}.
 */
public final class ArrayView<E> extends AbstractContainer<E> {

  private final E[] array;

  /**
   * Creates a new {@link ArrayView} for the given array.
   * 
   * @param array
   * @throws ArgumentIsNullException if the given array is null.
   */
  private ArrayView(final E[] array) {

    Validator.assertThat(array).thatIsNamed(LowerCaseVariableCatalog.ARRAY).isNotNull();

    this.array = array; //NOSONAR: An ArrayView operates on the original instance.
  }

  /**
   * @return a new {@link ArrayView} for a new empty array.
   * @param <E2> is the types of the elements of the array of the
   *             {@link ArrayView}.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ArrayView<E2> createEmpty() {
    return forArray((E2[]) new Object[0]);
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
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {

    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(0, getCount());

    return array[oneBasedIndex - 1];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return IntervallContainerView.forContainerAndStartIndexAndEndIndex(this, oneBasedStartIndex, oneBasedEndIndex);
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
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> comparableMapper) {
    return LinkedList.fromIterable(this).toOrderedList(comparableMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
