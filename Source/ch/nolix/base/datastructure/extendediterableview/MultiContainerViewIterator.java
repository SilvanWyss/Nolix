/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterableview;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent
 *            {@link MultiContainerView} of a
 *            {@link MultiContainerViewIterator}.
 */
public final class MultiContainerViewIterator<E> implements CopyableIterator<E> {
  private final CopyableIterator<ExtendedIterable<E>> mainIterator;

  private CopyableIterator<E> currentSubIterator;

  private MultiContainerViewIterator(final ExtendedIterable<ExtendedIterable<E>> containers) {
    mainIterator = containers.iterator();

    if (mainIterator.hasNext()) {
      currentSubIterator = mainIterator.next().iterator();
    }

    forwardSubIteratorToNextOrEnd();
  }

  private MultiContainerViewIterator(
    final CopyableIterator<ExtendedIterable<E>> mainIterator,
    final CopyableIterator<E> currentIterator) {
    this.mainIterator = mainIterator;
    this.currentSubIterator = currentIterator;
  }

  public static <T> MultiContainerViewIterator<T> forContainers(
    final ExtendedIterable<ExtendedIterable<T>> containers) {
    return new MultiContainerViewIterator<>(containers);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiContainerViewIterator<>(mainIterator.getCopy(), currentSubIterator.getCopy());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (currentSubIterator != null && currentSubIterator.hasNext());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT);
    }

    final var element = currentSubIterator.next();

    forwardSubIteratorToNextOrEnd();

    return element;
  }

  private void forwardSubIteratorToNextOrEnd() {
    while (currentSubIterator != null && !currentSubIterator.hasNext()) {
      if (mainIterator.hasNext()) {
        currentSubIterator = mainIterator.next().iterator();
      } else {
        currentSubIterator = null;
      }
    }
  }
}
