package ch.nolix.coreapi.container.base;

import java.util.function.Function;

import ch.nolix.coreapi.container.commoncontainer.AggregationRequestable;
import ch.nolix.coreapi.container.commoncontainer.ArrayMappable;
import ch.nolix.coreapi.container.commoncontainer.CountRequestable;
import ch.nolix.coreapi.container.commoncontainer.IndexRequestable;
import ch.nolix.coreapi.container.commoncontainer.IterableWithCopyableIterator;
import ch.nolix.coreapi.container.commoncontainer.SingleSearchable;
import ch.nolix.coreapi.container.commoncontainer.StoringRequestable;
import ch.nolix.coreapi.container.commoncontainer.StringMappable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

/**
 * A {@link IContainer} can store several elements of a certain type. A
 * {@link IContainer} stores its element in a linear order. There can exists
 * additional orders. A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements of a {@link IContainer}.
 */
public interface IContainer<E>
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
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} ordered from the smallest to the biggest element
   *         according to the {@link Comparable}s the given comparableMapper maps
   *         from the elements of the current {@link IContainer}.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link IContainer} is null.
   */
  <C extends Comparable<C>> IContainer<E> toOrderedList(Function<E, C> comparableMapper);

  /**
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} in reversed order.
   */
  IContainer<E> toReversedList();
}
