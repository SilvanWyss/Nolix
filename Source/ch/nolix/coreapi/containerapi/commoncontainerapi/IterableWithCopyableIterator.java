package ch.nolix.coreapi.containerapi.commoncontainerapi;

import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

/**
 * A {@link IterableWithCopyableIterator} is a {@link Iterable} that provide
 * {@link CopyableIterator}s.
 * 
 * @author Silvan Wyss
 * @version 2023-02-12
 * @param <E> is the type of the elements a
 *            {@link IterableWithCopyableIterator}.
 */
public interface IterableWithCopyableIterator<E> extends Iterable<E> {

  /**
   * {@inheritDoc}
   */
  @Override
  CopyableIterator<E> iterator();
}
