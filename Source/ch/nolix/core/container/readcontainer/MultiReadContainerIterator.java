//package declaration
package ch.nolix.core.container.readcontainer;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
final class MultiReadContainerIterator<E> implements CopyableIterator<E> {

  //attribute
  private final CopyableIterator<IContainer<E>> rootIterator;

  //optional attribute
  private CopyableIterator<E> currentIterator;

  //constructor
  public MultiReadContainerIterator(final Container<IContainer<E>> containers) {

    rootIterator = containers.iterator();

    if (rootIterator.hasNext()) {
      currentIterator = rootIterator.next().iterator();
    }
  }

  //constructor
  private MultiReadContainerIterator(
    final CopyableIterator<IContainer<E>> rootIterator,
    final CopyableIterator<E> currentIterator) {
    this.rootIterator = rootIterator;
    this.currentIterator = currentIterator;
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new MultiReadContainerIterator<>(rootIterator.getCopy(), currentIterator.getCopy());
  }

  //method
  @Override
  public boolean hasNext() {
    return (currentIterator != null && currentIterator.hasNext());
  }

  //method
  @Override
  public E next() {

    if (!hasNext()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT);
    }

    final var element = currentIterator.next();

    if (!currentIterator.hasNext()) {
      if (!rootIterator.hasNext()) {
        currentIterator = null;
      } else {
        currentIterator = rootIterator.next().iterator();
      }
    }

    return element;
  }
}
