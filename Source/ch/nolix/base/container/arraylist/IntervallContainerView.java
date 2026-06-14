/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.arraylist;

import ch.nolix.base.container.wellordercontainer.AbstractWellOrderContainer;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

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
 * @param <E> is the type of the elements of a {@link IntervallContainerView}.
 */
public final class IntervallContainerView<E> extends AbstractExtendedContainer<E> {
  private final AbstractWellOrderContainer<E> abstractWellOrderContainer;

  private final int startIndex;

  private final int endIndex;

  /**
   * Creates a new {@link IntervallContainerView} with the given container,
   * startIndex and endIndex.
   * 
   * @param container
   * @param startIndex
   * @param endIndex
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex.
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given container.
   */
  private IntervallContainerView(final AbstractWellOrderContainer<E> container, final int startIndex, final int endIndex) {
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

    this.abstractWellOrderContainer = container;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  /**
   * @param container
   * @param startIndex
   * @param endIndex
   * @param <T>        is the type of the elements of the created
   *                   {@link IntervallContainerView}.
   * @return a new {@link IntervallContainerView} with the given container,
   *         startIndex and endIndex.
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given endIndex is not positive.
   * @throws RuntimeException if the given endIndex is smaller than the given
   *                          startIndex.
   * @throws RuntimeException if the given endIndex is bigger than the number of
   *                          elements of the given container.
   */
  public static <T> IntervallContainerView<T> forContainerAndStartIndexAndEndIndex(
    final AbstractWellOrderContainer<T> container,
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

    return abstractWellOrderContainer.getStoredAtOneBasedIndex(startIndex + oneBasedIndex - 1);
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
    return //
    IntervallContainerViewIterator.forParentContainerAndStartIndexAndEndIndex(abstractWellOrderContainer, startIndex, endIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
