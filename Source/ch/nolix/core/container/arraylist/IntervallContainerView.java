package ch.nolix.core.container.arraylist;

import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;

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
public final class IntervallContainerView<E> extends AbstractExtendedContainer<E> {

  private final AbstractContainer<E> abstractContainer;

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
  private IntervallContainerView(final AbstractContainer<E> container, final int startIndex, final int endIndex) {

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

    this.abstractContainer = container;
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
    final AbstractContainer<E2> container,
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

    return abstractContainer.getStoredAtOneBasedIndex(startIndex + oneBasedIndex - 1);
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
      abstractContainer,
      startIndex,
      endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
