package ch.nolix.core.container.cachingcontainer;

import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

final class CachingContainerIterator<E> implements CopyableIterator<E> {

  private final CopyableIterator<Pair<String, E>> parentCachingContainerIterator;

  public CachingContainerIterator(final CopyableIterator<Pair<String, E>> parentCachingContainerIterator) {
    this.parentCachingContainerIterator = parentCachingContainerIterator;
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return new CachingContainerIterator<>(parentCachingContainerIterator.getCopy());
  }

  @Override
  public boolean hasNext() {
    return parentCachingContainerIterator.hasNext();
  }

  @Override
  public E next() {
    return parentCachingContainerIterator.next().getStoredElement2();
  }
}
