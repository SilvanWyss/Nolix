package ch.nolix.core.programstructure.caching;

import ch.nolix.core.container.pair.Pair;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

final class CachingContainerIterator<E> implements CopyableIterator<E> {

  private final CopyableIterator<Pair<String, E>> parentCachingContainerIterator;

  public CachingContainerIterator(final CopyableIterator<Pair<String, E>> parentCachingContainerIterator) {
    this.parentCachingContainerIterator = parentCachingContainerIterator;
  }

  @Override
  public CopyableIterator<E> createCopy() {
    return new CachingContainerIterator<>(parentCachingContainerIterator.createCopy());
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
