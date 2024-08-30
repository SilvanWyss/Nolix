//package declaration
package ch.nolix.core.container.readcontainer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
final class MultiReadContainerIterator<E> implements CopyableIterator<E> {

  //attribute
  private final CopyableIterator<IContainer<E>> mainIterator;

  //optional attribute
  private CopyableIterator<E> currentSubIterator;

  //constructor
  private MultiReadContainerIterator(final IContainer<IContainer<E>> containers) {

    mainIterator = containers.iterator();

    if (mainIterator.hasNext()) {
      currentSubIterator = mainIterator.next().iterator();
    }
  }

  //constructor
  private MultiReadContainerIterator(
    final CopyableIterator<IContainer<E>> rootIterator,
    final CopyableIterator<E> currentIterator) {
    this.mainIterator = rootIterator;
    this.currentSubIterator = currentIterator;
  }

  //static method
  public static <E2> MultiReadContainerIterator<E2> forContainers(
    final IContainer<IContainer<E2>> containers) {
    return new MultiReadContainerIterator<>(containers);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiReadContainerIterator<>(mainIterator.getCopy(), currentSubIterator.getCopy());
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

    if (!currentSubIterator.hasNext()) {
      if (!mainIterator.hasNext()) {
        currentSubIterator = null;
      } else {
        currentSubIterator = mainIterator.next().iterator();
      }
    }

    return element;
  }
}
