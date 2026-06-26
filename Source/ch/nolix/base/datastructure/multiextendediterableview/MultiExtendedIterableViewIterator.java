/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.multiextendediterableview;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent
 *            {@link MultiExtendedIterableView} of a
 *            {@link MultiExtendedIterableViewIterator}.
 */
public final class MultiExtendedIterableViewIterator<E> implements CopyableIterator<E> {
  private final CopyableIterator<ExtendedIterable<E>> mainIterator;

  private CopyableIterator<E> currentSubIterator;

  private MultiExtendedIterableViewIterator(final ExtendedIterable<ExtendedIterable<E>> containers) {
    mainIterator = containers.iterator();

    if (mainIterator.hasNext()) {
      currentSubIterator = mainIterator.next().iterator();
    }

    forwardSubIteratorToNextOrEnd();
  }

  private MultiExtendedIterableViewIterator(
    final CopyableIterator<ExtendedIterable<E>> mainIterator,
    final CopyableIterator<E> currentIterator) {
    this.mainIterator = mainIterator;
    this.currentSubIterator = currentIterator;
  }

  public static <T> MultiExtendedIterableViewIterator<T> forContainers(
    final ExtendedIterable<ExtendedIterable<T>> containers) {
    return new MultiExtendedIterableViewIterator<>(containers);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiExtendedIterableViewIterator<>(mainIterator.getCopy(), currentSubIterator.getCopy());
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.NEXT_ELEMENT);
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
