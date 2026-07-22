/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import ch.nolix.baseapi.datastructure.copyableiterator.IterableWithCopyableIterator;
import ch.nolix.baseapi.datastructure.iterableextension.IterableArrayProvider;
import ch.nolix.baseapi.datastructure.iterableextension.IterableAtIndexProvider;
import ch.nolix.baseapi.datastructure.iterableextension.IterableFirstProvider;
import ch.nolix.baseapi.datastructure.iterableextension.IterableLastProvider;
import ch.nolix.baseapi.datastructure.iterableextension.IterableStringProvider;
import ch.nolix.baseapi.datastructure.iterableextension.SingleSearchable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableContainAnyRequestable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableContainEqualRequestable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableContainMatchingRequestable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableContainMultipleRequestable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableContainObjectRequestable;
import ch.nolix.baseapi.datastructure.iterablerequest.IterableOneBasedIndexRequestable;
import ch.nolix.baseapi.datastructure.set.AggregationRequestable;
import ch.nolix.baseapi.datastructure.set.CountRequestable;
import ch.nolix.baseapi.datastructure.set.SingleProvider;
import ch.nolix.baseapi.state.staterequest.MaterializationRequestable;

/**
 * A {@link ExtendedIterable} can store several elements of a certain type. A
 * {@link ExtendedIterable} stores its element in a linear order. There can
 * exists additional orders. A {@link ExtendedIterable} is iterable.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ExtendedIterable}
 */
public interface ExtendedIterable<E>
extends
AggregationRequestable<E>,
CountRequestable<E>,
IterableArrayProvider<E>,
IterableAtIndexProvider<E>,
IterableContainAnyRequestable,
IterableContainEqualRequestable<E>,
IterableContainMatchingRequestable<E>,
IterableContainMultipleRequestable,
IterableContainObjectRequestable,
IterableFilterProvider<E>,
IterableFilterViewProvider<E>,
IterableFirstProvider<E>,
IterableGroupProvider<E>,
IterableIntervalViewProvider<E>,
IterableLastProvider<E>,
IterableMappedProvider<E>,
IterableMappedViewProvider<E>,
IterableOneBasedIndexRequestable<E>,
IterableOrderProvider<E>,
SingleProvider<E>,
IterableStringProvider,
IterableWithCopyableIterator<E>,
MaterializationRequestable,
SingleSearchable<E> {
  //This interface is a dedicated union of other interfaces.
}
