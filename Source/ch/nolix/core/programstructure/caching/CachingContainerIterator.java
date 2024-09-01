//package declaration
package ch.nolix.core.programstructure.caching;

//own imports
import ch.nolix.core.container.pair.Pair;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

//class
final class CachingContainerIterator<E> implements CopyableIterator<E> {

  //attribute
  private final CopyableIterator<Pair<String, E>> parentCachingContainerIterator;

  //constructor
  public CachingContainerIterator(final CopyableIterator<Pair<String, E>> parentCachingContainerIterator) {
    this.parentCachingContainerIterator = parentCachingContainerIterator;
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new CachingContainerIterator<>(parentCachingContainerIterator.getCopy());
  }

  //method
  @Override
  public boolean hasNext() {
    return parentCachingContainerIterator.hasNext();
  }

  //method
  @Override
  public E next() {
    return parentCachingContainerIterator.next().getStoredElement2();
  }
}
