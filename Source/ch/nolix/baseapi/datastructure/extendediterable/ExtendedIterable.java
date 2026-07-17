/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

import ch.nolix.baseapi.datastructure.baseextendediterable.ArrayMappable;
import ch.nolix.baseapi.datastructure.baseextendediterable.IndexRequestable;
import ch.nolix.baseapi.datastructure.baseextendediterable.IterableWithCopyableIterator;
import ch.nolix.baseapi.datastructure.baseextendediterable.SingleSearchable;
import ch.nolix.baseapi.datastructure.baseextendediterable.StringMappable;
import ch.nolix.baseapi.datastructure.general.AggregationRequestable;
import ch.nolix.baseapi.datastructure.general.CountRequestable;
import ch.nolix.baseapi.datastructure.iterablecontainrequest.IterableContainRequestable;
import ch.nolix.baseapi.state.staterequest.MaterializationRequestable;

/**
 * A {@link ExtendedIterable} can store several elements of a certain type. A
 * {@link ExtendedIterable} stores its element in a linear order. There can
 * exists additional orders. A {@link ExtendedIterable} is iterable.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ExtendedIterable}.
 */
public interface ExtendedIterable<E>
extends
AggregationRequestable<E>,
ArrayMappable<E>,
CountRequestable<E>,
Filterable<E>,
Groupable<E>,
FilteringContainerViewProvider<E>,
IntervallContainerViewProvider<E>,
IterableContainRequestable<E>,
MappingContainerViewProvider<E>,
IndexRequestable<E>,
IterableWithCopyableIterator<E>,
Mappable<E>,
MaterializationRequestable,
SingleSearchable<E>,
StringMappable {
  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper returns.
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link ExtendedIterable} ordered from the smallest to the biggest
   *         element according to the {@link Comparable}s the given
   *         comparableMapper maps from the elements of the current
   *         {@link ExtendedIterable}.
   * @throws RuntimeException if the given comparableMapper is null
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
