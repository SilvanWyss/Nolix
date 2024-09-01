//package declaration
package ch.nolix.core.container.containerview;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
final class MultiContainerViewIterator<E> implements CopyableIterator<E> {

  //attribute
  private final CopyableIterator<IContainer<E>> mainIterator;

  //optional attribute
  private CopyableIterator<E> currentSubIterator;

  //constructor
  private MultiContainerViewIterator(final IContainer<IContainer<E>> containers) {

    mainIterator = containers.iterator();

    if (mainIterator.hasNext()) {
      currentSubIterator = mainIterator.next().iterator();
    }

    forwardSubIteratorToNextOrEnd();
  }

  //constructor
  private MultiContainerViewIterator(
    final CopyableIterator<IContainer<E>> mainIterator,
    final CopyableIterator<E> currentIterator) {
    this.mainIterator = mainIterator;
    this.currentSubIterator = currentIterator;
  }

  //static method
  public static <E2> MultiContainerViewIterator<E2> forContainers(
    final IContainer<IContainer<E2>> containers) {
    return new MultiContainerViewIterator<>(containers);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiContainerViewIterator<>(mainIterator.getCopy(), currentSubIterator.getCopy());
  }

  //method
  @Override
  public boolean hasNext() {
    return (currentSubIterator != null && currentSubIterator.hasNext());
  }

  //method
  @Override
  public E next() {

    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT);
    }

    final var element = currentSubIterator.next();

    forwardSubIteratorToNextOrEnd();

    return element;
  }

  //method
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
