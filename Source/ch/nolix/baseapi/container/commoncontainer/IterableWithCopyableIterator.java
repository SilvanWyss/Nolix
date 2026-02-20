/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.commoncontainer;

import ch.nolix.baseapi.container.iterator.CopyableIterator;

/**
 * A {@link IterableWithCopyableIterator} is a {@link Iterable} that provide
 * {@link CopyableIterator}s.
 * 
 * @author Silvan Wyss
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
