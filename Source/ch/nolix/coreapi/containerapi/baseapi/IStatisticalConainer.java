//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

//interface
/**
 * @author Silvan Wyss
 * @version 2023-10-30
 * @param <E> is the type of the elements a {@link IStatisticalConainer}.
 */
public interface IStatisticalConainer<E> {

  //method declaration
  /**
   * @param norm
   * @return the average of the values the given norm returns from the elements of
   *         the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getAverage(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the average of the values the given norm returns from the elements of
   *         the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains elements, 0.0 otherwise.
   */
  double getAverageOrZero(Function<E, Number> norm);

  //method declaration declaration
  /**
   * @return the number of elements of the current {@link IStatisticalConainer}.
   */
  int getCount();

  //method declaration
  /**
   * @param selector
   * @return the number of elements the given selector selects from the current
   *         {@link IStatisticalConainer}.
   */
  int getCount(Predicate<E> selector);

  //method declaration
  /**
   * @param element
   * @return the number how many times the current {@link IStatisticalConainer}
   *         contains the given element.
   */
  int getCount(Object element);

  //method declaration
  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the biggest {@link Comparable} the given norm returns from the
   *         elements of the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  <C extends Comparable<C>> C getMax(Function<E, C> norm);

  //method declaration
  /**
   * @param norm
   * @return the biggest value the given norm returns from the elements of the
   *         current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains elements, 0.0 otherwise.
   */
  double getMaxOrZero(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the median of the values the given norm returns from the elements of
   *         the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getMedian(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the median of the values the given norm returns from the elements of
   *         the current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains elements, 0.0 otherwise.
   */
  double getMedianOrZero(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the smallest {@link Comparable} the given norm returns from the
   *         elements of the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  <C extends Comparable<C>> C getMin(Function<E, C> norm);

  //method declaration
  /**
   * @param norm
   * @return the smallest value the given norm returns from the elements of the
   *         current {@link IStatisticalConainer} if the current
   *         {@link IStatisticalConainer} contains elements, 0.0 otherwise.
   */
  double getMinOrZero(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the standard deviation of the values the given norm returns from the
   *         elements of the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getStandardDeviation(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the sum of the values the given norm returns from the elements of the
   *         current {@link IStatisticalConainer}.
   */
  BigDecimal getSum(Function<E, Number> norm);

  //method declaration
  /**
   * @param norm
   * @return the sum of the integers the given norm returns from the elements of
   *         the current {@link IStatisticalConainer}.
   */
  BigInteger getSumOfIntegers(ToIntFunction<E> norm);

  //method declaration
  /**
   * @param norm
   * @return the variance of the values the given norm returns from the elements
   *         of the current {@link IStatisticalConainer}.
   * @throws RuntimeException if the current {@link IStatisticalConainer} is
   *                          empty.
   */
  double getVariance(Function<E, Number> norm);
}
