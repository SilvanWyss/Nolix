/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablerequest;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableRequestable}
 */
public interface IterableRequestable<E>
extends
ContainAnyRequestable,
ContainEqualRequestable<E>,
ContainMatchingRequestable<E>,
ContainMultipleRequestable,
ContainObjectRequestable {
  //This interface is a dedicated union of other interfaces.
}
