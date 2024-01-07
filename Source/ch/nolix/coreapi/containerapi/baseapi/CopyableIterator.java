//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.util.Iterator;

import ch.nolix.coreapi.programstructureapi.builderapi.Copyable;

//interface
/**
 * A {@link CopyableIterator} is a {@link Iterator} that can be copied.
 * 
 * @author Silvan Wyss
 * @date 2023-02-12
 * @param <E> is the type of the elements a {@link CopyableIterator}.
 */
public interface CopyableIterator<E> extends Copyable<CopyableIterator<E>>, Iterator<E> {
}
