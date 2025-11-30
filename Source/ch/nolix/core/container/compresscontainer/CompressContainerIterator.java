package ch.nolix.core.container.compresscontainer;

import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

final class CompressContainerIterator<E> implements CopyableIterator<E> {
  private final CopyableIterator<Pair<String, E>> parentCompressContainerIterator;

  public CompressContainerIterator(final CopyableIterator<Pair<String, E>> parentCompressContainerIterator) {
    this.parentCompressContainerIterator = parentCompressContainerIterator;
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return new CompressContainerIterator<>(parentCompressContainerIterator.getCopy());
  }

  @Override
  public boolean hasNext() {
    return parentCompressContainerIterator.hasNext();
  }

  @Override
  public E next() {
    return parentCompressContainerIterator.next().getStoredElement2();
  }
}
