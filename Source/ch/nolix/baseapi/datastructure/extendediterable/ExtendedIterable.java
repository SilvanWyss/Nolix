/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

import ch.nolix.baseapi.datastructure.copyableiterator.IterableWithCopyableIterator;
import ch.nolix.baseapi.datastructure.iterableextension.IterableArrayProvider;
import ch.nolix.baseapi.datastructure.iterableextension.IterableFirstProvider;
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
IterableMappedProvider<E>,
IterableMappedViewProvider<E>,
IterableOneBasedIndexRequestable<E>,
IterableStringProvider,
IterableWithCopyableIterator<E>,
MaterializationRequestable,
SingleSearchable<E> {
  /**
   * @param comparableMapper
   * @param <C>              the type of the {@link Comparable}s the given
   *                         comparableMapper returns
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link ExtendedIterable} ordered from the smallest to the biggest
   *         element according to the {@link Comparable}s the given
   *         comparableMapper maps from the elements of the current
   *         {@link ExtendedIterable}
   * @throws RuntimeException if the given comparableMapper is null
   * @throws RuntimeException if one of the elements of the current
   *                          {@link ExtendedIterable} is null
   */
  <C extends Comparable<C>> ExtendedIterable<E> toOrdered(Function<E, C> comparableMapper);

  /**
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link ExtendedIterable} in reversed order
   */
  ExtendedIterable<E> toReversed();
}
