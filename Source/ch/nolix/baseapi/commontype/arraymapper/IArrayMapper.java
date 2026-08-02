/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.arraymapper;

import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import ch.nolix.baseapi.commontype.function.ToByteFunction;

/**
 * @author Silvan Wyss
 */
public interface IArrayMapper {
  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable   the mapped iterable, is considered to be empty when is null
   * @param n
   * @param byteMapper
   * @param <E>        the type of the elements of the given iterable
   * @return a new array with the first n bytes the given byteMapper maps from the
   *         elements of the given iterable, mapping null elements to 0
   * @throws RuntimeException if the given n is negative or bigger than the size
   *                          of the given iterable
   * @throws RuntimeException if the given byteMapper is null
   */
  <E> byte[] toByteArray(Iterable<E> iterable, int n, ToByteFunction<E> byteMapper);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable     the mapped iterable, is considered to be empty when is
   *                     null
   * @param n
   * @param doubleMapper
   * @param <E>          the type of the elements of the given iterable
   * @return a new array with the first n doubles the given doubleMapper maps from
   *         the elements of the given iterable, mapping null elements to 0.0
   * @throws RuntimeException if the given n is negative or bigger than the size
   *                          of the given iterable
   * @throws RuntimeException if the given doubleMapper is null
   */
  <E> double[] toDoubleArray(Iterable<E> iterable, int n, ToDoubleFunction<E> doubleMapper);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable  the mapped iterable, is considered to be empty when is null
   * @param n
   * @param intMapper
   * @param <E>       the type of the elements of the given iterable
   * @return a new array with the first n ints the given intMapper maps from the
   *         elements of the given iterable, mapping null elements to 0
   * @throws RuntimeException if the given n is negative or bigger than the size
   *                          of the given iterable
   * @throws RuntimeException if the given intMapper is null
   */
  <E> int[] toIntArray(Iterable<E> iterable, int n, ToIntFunction<E> intMapper);
}
