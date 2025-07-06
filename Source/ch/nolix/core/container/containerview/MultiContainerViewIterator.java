package ch.nolix.core.container.containerview;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

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

  public static <E2> MultiContainerViewIterator<E2> forContainers(
    final IContainer<IContainer<E2>> containers) {
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
