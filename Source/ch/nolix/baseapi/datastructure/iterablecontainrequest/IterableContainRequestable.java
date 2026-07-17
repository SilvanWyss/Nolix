/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablecontainrequest;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableContainRequestable}
 */
public interface IterableContainRequestable<E>
extends
IterableContainAnyRequestable,
IterableContainEqualRequestable<E>,
IterableContainMatchingRequestable<E>,
IterableContainMultipleRequestable,
IterableContainObjectRequestable {
  //This interface is a dedicated union of other interfaces.
}
