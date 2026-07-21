/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link IterableIntervalViewProvider}
 */
public interface IterableIntervalViewProvider<E> {
  /**
   * @param oneBasedStartIndex
   * @return a new view of the current {@link IterableIntervalViewProvider} from
   *         the given oneBasedStartIndex
   * @throws RuntimeException if the given oneBasedStartIndex is bigger than the
   *                          number of elements of the current
   *                          {@link ExtendedIterable}.
   */
  ExtendedIterable<E> getViewFromOneBasedStartIndex(int oneBasedStartIndex);

  /**
   * @param oneBasedStartIndex
   * @param oneBasedEndIndex
   * @return a new view of the current {@link IterableIntervalViewProvider} from
   *         the given oneBasedStartIndex to the given oneBasedEndIndex
   * @throws RuntimeException if the given oneBasedStartIndex is not positive
   * @throws RuntimeException if the given oneBasedStartIndex is smaller than the
   *                          given oneBasedEndIndex
   * @throws RuntimeException if the given oneBasedEndIndex is bigger than the
   *                          number of elements of the current
   *                          {@link ExtendedIterable}.
   */
  ExtendedIterable<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(int oneBasedStartIndex, int oneBasedEndIndex);

  /**
   * @param oneBasedEndIndex
   * @return a new view {@link ExtendedIterable} of the current
   *         {@link IterableIntervalViewProvider} to the given oneBasedEndIndex
   * @throws RuntimeException if the given oneBasedEndIndex is not positive
   * @throws RuntimeException if the given oneBasedEndIndex is bigger than the
   *                          number of the elements of the current
   *                          {@link ExtendedIterable}.
   */
  ExtendedIterable<E> getViewToOneBasedEndIndex(int oneBasedEndIndex);

  /**
   * @return a new view {@link ExtendedIterable} view of the current
   *         {@link IterableIntervalViewProvider} without the first element
   * @throws RuntimeException if the current
   *                          {@link IterableIntervalViewProvider} is empty
   */
  ExtendedIterable<E> getViewWithoutFirst();

  /**
   * @param n
   * @return a new view {@link ExtendedIterable} view of the current
   *         {@link IterableIntervalViewProvider} without the first n elements
   * @throws RuntimeException if the given n is negative
   */
  ExtendedIterable<E> getViewWithoutFirst(int n);

  /**
   * @return a new view {@link ExtendedIterable} view of the current
   *         {@link IterableIntervalViewProvider} without the last element
   * @throws RuntimeException if the current
   *                          {@link IterableIntervalViewProvider} is empty
   */
  ExtendedIterable<E> getViewWithoutLast();

  /**
   * @param n
   * @return a new view {@link ExtendedIterable} of the current
   *         {@link IterableIntervalViewProvider} without the last n elements
   * @throws RuntimeException if the given n is negative
   */
  ExtendedIterable<E> getViewWithoutLast(int n);
}
