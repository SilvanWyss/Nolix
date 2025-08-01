package ch.nolix.coreapi.container.iterator;

import java.util.Iterator;

import ch.nolix.coreapi.objectcreation.copier.Copyable;

/**
 * A {@link CopyableIterator} is a {@link Iterator} that can be copied.
 * 
 * @author Silvan Wyss
 * @version 2023-02-12
 * @param <E> is the type of the elements a {@link CopyableIterator}.
 */
public interface CopyableIterator<E> extends Copyable<CopyableIterator<E>>, Iterator<E> {
}
