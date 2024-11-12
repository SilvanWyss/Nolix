package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

import ch.nolix.coreapi.containerapi.commoncontainerapi.ArrayMappable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IStatisticalConainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IndexRequestable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.IterableWithCopyableIterator;
import ch.nolix.coreapi.containerapi.commoncontainerapi.SingleSearchable;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StoringRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

/**
 * A {@link IContainer} can store several elements of a certain type. A
 * {@link IContainer} stores its element in a linear order. There can exists
 * additionally other orders. A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements a {@link IContainer}.
 */
public interface IContainer<E>
extends
ArrayMappable<E>,
EmptinessRequestable,
IndexRequestable<E>,
IStatisticalConainer<E>,
IterableWithCopyableIterator<E>,
IViewProviderContainer<E>,
Mappable<E>,
MaterializationRequestable,
MultiSearchable<E>,
SingleSearchable<E>,
StoringRequestable<E> {

  /**
   * @return a new array with the elements of the current {@link IContainer}.
   */
  Object[] toArray();

  /**
   * @return a concatenation of the {@link String} representations of the elements
   *         of the current {@link IContainer}.
   */
  String toConcatenatedString();

  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} ordered from the smallest to the biggest element
   *         according to the given norm.
   */
  <C extends Comparable<C>> IContainer<E> toOrderedList(Function<E, C> norm);

  /**
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} in reversed order.
   */
  IContainer<E> toReversedList();

  /**
   * @param separator
   * @return a {@link String} representation of the current {@link IContainer}
   *         with the given separator.
   */
  String toStringWithSeparator(char separator);

  /**
   * @param separator
   * @return a {@link String} representation of the current {@link IContainer}
   *         with the given separator.
   * @throws RuntimeException if the given separator is null.
   */
  String toStringWithSeparator(String separator);
}
