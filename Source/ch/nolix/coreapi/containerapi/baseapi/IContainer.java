package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

import ch.nolix.coreapi.containerapi.commoncontainerapi.ArrayMappable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.ICountingContainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IStatisticalConainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IndexRequestable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IterableWithCopyableIterator;
import ch.nolix.coreapi.containerapi.commoncontainerapi.SingleSearchable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StoringRequestable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StringMappable;
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
ArrayMappable<E>,
EmptinessRequestable,
ICountingContainer<E>,
IFilter<E>,
IndexRequestable<E>,
IStatisticalConainer<E>,
IterableWithCopyableIterator<E>,
IIntervallContainerViewProvider<E>,
IMappingProvider<E>,
MaterializationRequestable,
MultiSearchable<E>,
SingleSearchable<E>,
StoringRequestable<E>,
StringMappable,
IMappingContainerViewProvider<E>,
IFilterContainerViewProvider<E> {

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
