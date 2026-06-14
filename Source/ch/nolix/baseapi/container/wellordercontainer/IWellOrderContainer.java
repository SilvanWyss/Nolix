/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.container.wellordercontainer;

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
 * A {@link IWellOrderContainer} can store several elements of a certain type. A
 * {@link IWellOrderContainer} stores its element in a linear order. There can exists
 * additional orders. A {@link IWellOrderContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link IWellOrderContainer}.
 */
public interface IWellOrderContainer<E>
extends
AggregationRequestable<E>,
ArrayMappable<E>,
CountRequestable<E>,
EmptinessRequestable,
Filterable<E>,
Groupable<E>,
IFilteringContainerViewProvider<E>,
IIntervallContainerViewProvider<E>,
IMappingContainerViewProvider<E>,
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
   * @return a new {@link IWellOrderContainer} with the elements of the current
   *         {@link IWellOrderContainer} ordered from the smallest to the biggest element
   *         according to the {@link Comparable}s the given comparableMapper maps
   *         from the elements of the current {@link IWellOrderContainer}.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link IWellOrderContainer} is null.
   */
  <C extends Comparable<C>> IWellOrderContainer<E> toOrderedList(Function<E, C> comparableMapper);

  /**
   * @return a new {@link IWellOrderContainer} with the elements of the current
   *         {@link IWellOrderContainer} in reversed order.
   */
  IWellOrderContainer<E> toReversedList();
}
