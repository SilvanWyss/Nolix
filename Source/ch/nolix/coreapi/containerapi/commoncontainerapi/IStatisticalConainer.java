package ch.nolix.coreapi.containerapi.commoncontainerapi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @author Silvan Wyss
 * @version 2023-10-30
 * @param <E> is the type of the elements a {@link IStatisticalConainer}.
 */
public interface IStatisticalConainer<E> {

  /**
   * @param valueMapper
   * @return the average of the values the given valueMapper maps from the
   *         elements of the current {@link IStatisticalConainer}. Maps null
   *         elements to 0.0.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getAverage(Function<E, Number> valueMapper);

  /**
   * @param valueMapper
   * @return the average of the values the given valueMapper maps from the
   *         elements of the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains any, 0.0 otherwise. Maps null
   *         elements to 0.0.
   * @throws RuntimeException if the given valueMapper is null.
   */
  double getAverageOrZero(Function<E, Number> valueMapper);

  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link IStatisticalConainer}.
   * @return the biggest {@link Comparable} the given comparableMapper maps from
   *         the elements of the current {@link IStatisticalConainer}. Ignores
   *         null elements.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> C getMax(Function<E, C> comparableMapper);

  /**
   * @param numberMapper
   * @return the biggest number the given numberMapper maps from the non-null
   *         elements of the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains non-null elements, 0.0
   *         otherwise.
   */
  double getMaxOrZero(Function<E, Number> numberMapper);

  /**
   * @param numberMapper
   * @return the median of the numbers the given numberMapper maps from the
   *         elements of the current {@link IStatisticalConainer}. Maps null
   *         elements to 0.0.
   * @throws RuntimeException if the given numberMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getMedian(Function<E, Number> numberMapper);

  /**
   * @param numberMapper
   * @return the median of the numbers the given numberMapper maps from the
   *         elements of the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} is not empty, 0.0 otherwise. Maps null
   *         elements to 0.0.
   * @throws RuntimeException if the given numberMapper is null.
   */
  double getMedianOrZero(Function<E, Number> numberMapper);

  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link IStatisticalConainer}.
   * @return the smallest {@link Comparable} the given comparableMapper maps from
   *         the elements of the current {@link IStatisticalConainer}. Ignores
   *         null elements.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> C getMin(Function<E, C> comparableMapper);

  /**
   * @param numberMapper
   * @return the smallest number the given numberMapper maps from the non-null
   *         elements of the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains non-null elements, 0.0
   *         otherwise.
   */
  double getMinOrZero(Function<E, Number> numberMapper);

  /**
   * @param numberMapper
   * @return the standard deviation of the numbers the given numberMapper maps
   *         from the elements of the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the given numberMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getStandardDeviation(Function<E, Number> numberMapper);

  /**
   * @param valueMapper
   * @return the sum of the values the given valueMapper maps from the elements of
   *         the current {@link IStatisticalConainer}. Maps null elements to 0.0.
   * @throws RuntimeException if the given valueMapper is null.
   */
  BigDecimal getSum(Function<E, Number> valueMapper);

  /**
   * @param intMapper
   * @return the sum of the ints the given intMapper maps from the elements of the
   *         current {@link IStatisticalConainer}. Maps null elements to 0.0.
   * @throws RuntimeException if the given intMapper is null.
   */
  BigInteger getSumOfInts(ToIntFunction<E> intMapper);

  /**
   * @param numberMapper
   * @return the variance of the numbers the given numberMapper maps from the
   *         elements of the current {@link IStatisticalConainer}. Maps null
   *         elements to 0.0.
   * @throws RuntimeException if the given numberMapper is null.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getVariance(Function<E, Number> numberMapper);
}
