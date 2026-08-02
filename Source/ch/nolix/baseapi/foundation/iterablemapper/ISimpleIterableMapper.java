/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.foundation.iterablemapper;

/**
 * A {@link ISimpleIterableMapper} provides functions to handle arrays.
 * 
 * @author Silvan Wyss
 */
public interface ISimpleIterableMapper {
  /**
   * @param bytes
   * @return a new {@link Iterable} with the given bytes
   * @throws RuntimeException if the given bytes is null
   */
  Iterable<Byte> toIterable(byte[] bytes);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values
   * @throws RuntimeException if the given values is null
   */
  Iterable<Double> toIterable(double[] values);

  /**
   * @param elements
   * @param <E>      the type of the given elements
   * @return a new {@link Iterable} with the given elements
   * @throws RuntimeException if the given elements is null
   */
  <E> Iterable<E> toIterable(final E[] elements);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values
   * @throws RuntimeException if the given values is null
   */
  Iterable<Long> toIterable(int[] values);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values
   * @throws RuntimeException if the given values is null
   */
  Iterable<Long> toIterable(long[] values);
}
