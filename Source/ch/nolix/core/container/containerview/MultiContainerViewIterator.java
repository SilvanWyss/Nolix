package ch.nolix.core.container.containerview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent
 *            {@link MultiContainerView} of a
 *            {@link MultiContainerViewIterator}.
 */
public final class MultiContainerViewIterator<E> implements CopyableIterator<E> {
  private final CopyableIterator<IContainer<E>> mainIterator;

  private CopyableIterator<E> currentSubIterator;

  private MultiContainerViewIterator(final IContainer<IContainer<E>> containers) {
    mainIterator = containers.iterator();

    if (mainIterator.hasNext()) {
      currentSubIterator = mainIterator.next().iterator();
    }

    forwardSubIteratorToNextOrEnd();
  }

  private MultiContainerViewIterator(
    final CopyableIterator<IContainer<E>> mainIterator,
    final CopyableIterator<E> currentIterator) {
    this.mainIterator = mainIterator;
    this.currentSubIterator = currentIterator;
  }

  public static <T> MultiContainerViewIterator<T> forContainers(
    final IContainer<IContainer<T>> containers) {
    return new MultiContainerViewIterator<>(containers);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiContainerViewIterator<>(mainIterator.getCopy(), currentSubIterator.getCopy());
  }

  @Override
  public boolean hasNext() {
    return (currentSubIterator != null && currentSubIterator.hasNext());
  }

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
