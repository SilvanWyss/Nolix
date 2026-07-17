/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableextension;

import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;

/**
 * A {@link IterableWithCopyableIterator} is a {@link Iterable} that provide
 * {@link CopyableIterator}s.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements a
 *            {@link IterableWithCopyableIterator}.
 */
public interface IterableWithCopyableIterator<E> extends Iterable<E> {
  /**
   * {@inheritDoc}
   */
  @Override
  CopyableIterator<E> iterator();
}
