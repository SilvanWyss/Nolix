/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterableprovider;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableArrayProvider}
 */
public interface IterableArrayProvider<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @return a new array with the elements of the current
   *         {@link IterableArrayProvider}
   */
  Object[] toArray();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @param byteMapper
   * @return a new array with the bytes the given byteMapper maps from the
   *         elements of the current {@link IterableArrayProvider}. Maps null
   *         elements to 0
   * @throws RuntimeException if the given byteMapper is null
   */
  byte[] toByteArray(Function<E, Byte> byteMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @param charMapper
   * @return a new array with the chars the given charMapper maps from the
   *         elements of the current {@link IterableArrayProvider}. Maps null
   *         elements to a space
   * @throws RuntimeException if the given charMapper is null
   */
  char[] toCharArray(Function<E, Character> charMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @param doubleMapper
   * @return a new array with the doubles the given doubleMapper maps from the
   *         elements of the current {@link IterableArrayProvider}. Maps null
   *         elements to 0.0
   * @throws RuntimeException if the given doubleMapper is null
   */
  double[] toDoubleArray(ToDoubleFunction<E> doubleMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @param intMapper
   * @return a new array with the ints the given intMapper maps from the elements
   *         of the current {@link IterableArrayProvider}. Maps null elements to
   *         0. Maps null elements to 0
   * @throws RuntimeException if the given intMapper is null
   */
  int[] toIntArray(ToIntFunction<E> intMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @param longMapper
   * @return a new array with the longs the given longMapper maps from the
   *         elements of the current {@link IterableArrayProvider}. Maps null
   *         elements to 0
   * @throws RuntimeException if the given longMapper is null
   */
  long[] toLongArray(ToLongFunction<E> longMapper);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableArrayProvider} contains n elements.
   * 
   * @return a new array with the {@link String} representations of the elements
   *         of the current {@link IterableArrayProvider}.
   */
  String[] toStringArray();
}
