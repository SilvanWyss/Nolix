/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

import ch.nolix.baseapi.container.basewellordercontainer.ArrayMappable;
import ch.nolix.baseapi.container.basewellordercontainer.IndexRequestable;
import ch.nolix.baseapi.container.basewellordercontainer.IterableWithCopyableIterator;
import ch.nolix.baseapi.container.basewellordercontainer.SingleSearchable;
import ch.nolix.baseapi.container.basewellordercontainer.StoringRequestable;
import ch.nolix.baseapi.container.basewellordercontainer.StringMappable;
import ch.nolix.baseapi.container.generalcontainer.AggregationRequestable;
import ch.nolix.baseapi.container.generalcontainer.CountRequestable;
import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;
import ch.nolix.baseapi.state.staterequest.MaterializationRequestable;

/**
 * A {@link ExtendedIterable} can store several elements of a certain type. A
 * {@link ExtendedIterable} stores its element in a linear order. There can exists
 * additional orders. A {@link ExtendedIterable} is iterable.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ExtendedIterable}.
 */
public interface ExtendedIterable<E>
extends
AggregationRequestable<E>,
ArrayMappable<E>,
CountRequestable<E>,
EmptinessRequestable,
Filterable<E>,
Groupable<E>,
FilteringContainerViewProvider<E>,
IntervallContainerViewProvider<E>,
MappingContainerViewProvider<E>,
IndexRequestable<E>,
IterableWithCopyableIterator<E>,
Mappable<E>,
MaterializationRequestable,
SingleSearchable<E>,
StoringRequestable<E>,
StringMappable {
  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper returns.
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link ExtendedIterable} ordered from the smallest to the biggest element
   *         according to the {@link Comparable}s the given comparableMapper maps
   *         from the elements of the current {@link ExtendedIterable}.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link ExtendedIterable} is null.
   */
  <C extends Comparable<C>> ExtendedIterable<E> toOrderedList(Function<E, C> comparableMapper);

  /**
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link ExtendedIterable} in reversed order.
   */
  ExtendedIterable<E> toReversedList();
}
