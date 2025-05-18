package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link IntervallContainerView} can iterate over a part of another
 * container.
 * 
 * A {@link IntervallContainerView} must not use the methods of the accessed
 * container except the iterator method. The reason is that the accessed
 * container can be a specialized container that does not use its iterator in
 * any of its declared or overwritten method.
 * 
 * @author Silvan Wyss
 * @version 2017-08-27
 * @param <E> is the type of the elements of a {@link IntervallContainerView}.
 */
public final class IntervallContainerView<E> extends Container<E> {

  private final Container<E> container;

  private final int startIndex;

  private final int endIndex;

  /**
   * Creates a new {@link IntervallContainerView} with the given container,
   * startIndex and endIndex.
   * 
   * @param container
   * @param startIndex
   * @param endIndex
   * @throws ArgumentIsNullException      if the given container is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   * @throws SmallerArgumentException     if the given endIndex is smaller than
   *                                      the given startIndex.
   * @throws BiggerArgumentException      if the given endIndex is bigger than the
   *                                      number of elements of the given
   *                                      container.
   */
  private IntervallContainerView(final Container<E> container, final int startIndex, final int endIndex) {

    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalog.END_INDEX).isPositive();

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalog.END_INDEX)
      .isBiggerThanOrEquals(startIndex);

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalog.END_INDEX)
      .isNotBiggerThan(container.getCount());

    this.container = container;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  /**
   * @param container
   * @param startIndex
   * @param endIndex
   * @param <E2>       is the type of the elements of the created
   *                   {@link IntervallContainerView}.
   * @return a new {@link IntervallContainerView} with the given container,
   *         startIndex and endIndex.
   * @throws ArgumentIsNullException      if the given container is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   * @throws SmallerArgumentException     if the given endIndex is smaller than
   *                                      the given startIndex.
   * @throws BiggerArgumentException      if the given endIndex is bigger than the
   *                                      number of elements of the given
   *                                      container.
   */
  public static <E2> IntervallContainerView<E2> forContainerAndStartIndexAndEndIndex(
    final Container<E2> container,
    final int startIndex,
    final int endIndex) {
    return new IntervallContainerView<>(container, startIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return (endIndex - startIndex + 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {

    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableCatalog.INDEX).isPositive();

    Validator
      .assertThat(oneBasedIndex)
      .thatIsNamed(LowerCaseVariableCatalog.INDEX)
      .isNotBiggerThan(getCount());

    return container.getStoredAtOneBasedIndex(startIndex + oneBasedIndex - 1);
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
    return new IntervallContainerViewIterator<>(
      container,
      startIndex,
      endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
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
