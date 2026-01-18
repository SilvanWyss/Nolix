/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.container.iterator;

import java.util.Iterator;

import ch.nolix.coreapi.objectcreation.copier.Copyable;

/**
 * A {@link CopyableIterator} is a {@link Iterator} that can be copied.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements a {@link CopyableIterator}.
 */
public interface CopyableIterator<E> extends Copyable<CopyableIterator<E>>, Iterator<E> {
  //This interface is just an union of other interfaces.
}
