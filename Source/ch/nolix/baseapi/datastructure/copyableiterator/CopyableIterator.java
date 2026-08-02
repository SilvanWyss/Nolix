/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.copyableiterator;

import java.util.Iterator;

import ch.nolix.baseapi.objectcomposition.copier.Copyable;

/**
 * A {@link CopyableIterator} is a {@link Iterator} that can be copied.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link CopyableIterator}.
 */
public interface CopyableIterator<E> extends Copyable<CopyableIterator<E>>, Iterator<E> {
  // This interface is a dedicated union of other interfaces.
}
