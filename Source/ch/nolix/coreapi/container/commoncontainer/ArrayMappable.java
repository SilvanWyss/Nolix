package ch.nolix.coreapi.container.commoncontainer;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author Silvan Wyss
 * @version 2024-11-12
 * @param <E> is the type of the elements a {@link ArrayMappable}.
 */
public interface ArrayMappable<E> {

  /**
   * @return a new array with the elements of the current {@link ArrayMappable}.
   */
  Object[] toArray();

  /**
   * @param byteMapper
   * @return a new array with the bytes the given byteMapper maps from the
   *         elements of the current {@link ArrayMappable}. Maps null elements to
   *         0.
   * @throws RuntimeException if the given byteMapper is null.
   */
  byte[] toByteArray(Function<E, Byte> byteMapper);

  /**
   * @param charMapper
   * @return a new array with the chars the given charMapper maps from the
   *         elements of the current {@link ArrayMappable}. Maps null elements to
   *         a space.
   * @throws RuntimeException if the given charMapper is null.
   */
  char[] toCharArray(Function<E, Character> charMapper);

  /**
   * @param doubleMapper
   * @return a new array with the doubles the given doubleMapper maps from the
   *         elements of the current {@link ArrayMappable}. Maps null elements to
   *         0.0.
   * @throws RuntimeException if the given doubleMapper is null.
   */
  double[] toDoubleArray(ToDoubleFunction<E> doubleMapper);

  /**
   * @param intMapper
   * @return a new array with the ints the given intMapper maps from the elements
   *         of the current {@link ArrayMappable}. Maps null elements to 0. Maps
   *         null elements to 0.
   * @throws RuntimeException if the given intMapper is null.
   */
  int[] toIntArray(ToIntFunction<E> intMapper);

  /**
   * @param longMapper
   * @return a new array with the longs the given longMapper maps from the
   *         elements of the current {@link ArrayMappable}. Maps null elements to
   *         0.
   * @throws RuntimeException if the given longMapper is null.
   */
  long[] toLongArray(ToLongFunction<E> longMapper);

  /**
   * @return a new array with the {@link String} representations of the elements
   *         of the current {@link ArrayMappable}.
   */
  String[] toStringArray();
}
