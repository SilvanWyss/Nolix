package ch.nolix.core.container.base;

import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-08-27
 * @param <E> is the type of the elements of a {@link ContainerViewIterator}.
 */
final class ContainerViewIterator<E> implements CopyableIterator<E> {

  private final IContainer<E> parentContainer;

  private final int endIndex;

  private final Iterator<E> iterator;

  private int currentIndex;

  /**
   * Creates a new {@link ContainerViewIterator} for the given parentContainer,
   * startIndex and endIndex.
   * 
   * @param parentContainer
   * @param startIndex
   * @param endIndex
   * @throws ArgumentIsNullException      if the given parentContainer is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   */
  public ContainerViewIterator(final IContainer<E> parentContainer, final int startIndex, final int endIndex) {

    Validator.assertThat(parentContainer).thatIsNamed("parent container").isNotNull();
    Validator.assertThat(startIndex).thatIsNamed(LowerCaseVariableCatalog.START_INDEX).isPositive();
    Validator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalog.END_INDEX).isPositive();

    Validator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalog.END_INDEX)
      .isBiggerThanOrEquals(startIndex);

    this.parentContainer = parentContainer;

    this.endIndex = endIndex;

    currentIndex = startIndex;

    iterator = parentContainer.iterator();
    for (var i = 1; i < startIndex; i++) {
      iterator.next();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> createCopy() {
    return new ContainerViewIterator<>(parentContainer, currentIndex, endIndex);
  }

  /**
   * @return true if the current {@link ContainerViewIterator} has a next element.
   */
  @Override
  public boolean hasNext() {
    return (currentIndex <= endIndex);
  }

  /**
   * @return the next element of the current {@link ContainerViewIterator}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ContainerViewIterator}
   *                                               does not have a next element.
   */
  @Override
  public E next() {

    //Asserts that the current @link SubContainerIterator has a next element.
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT);
    }

    currentIndex++;
    return iterator.next();
  }
}
