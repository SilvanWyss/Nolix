package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link IMappableContainer}.
 */
public interface IMappableContainer<E> {

  /**
   * @param extractor
   * @param <E2>      is the type of the elements the given extractor returns.
   * @return a new {@link IMappableContainer} with the elements the given
   *         extractor extracts from the elements of the current
   *         {@link IMappableContainer}.
   */
  <E2> IContainer<E2> to(Function<E, E2> extractor);

  /**
   * @return a new array with the elements of the current
   *         {@link IMappableContainer}.
   */
  Object[] toArray();

  /**
   * @param byteGetter
   * @return a new array with the bytes the given byteGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  byte[] toByteArray(Function<E, Byte> byteGetter);

  /**
   * @param charGetter
   * @return a new array with the chars the given charGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  char[] toCharArray(Function<E, Character> charGetter);

  /**
   * @return a concatenated {@link String} representation of the current
   *         {@link IMappableContainer}.
   */
  String toConcatenatedString();

  /**
   * @param doubleGetter
   * @return a new array with the doubles the given doubleGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  double[] toDoubleArray(ToDoubleFunction<E> doubleGetter);

  /**
   * @param extractor
   * @param <E2>      is the type of the elements of the
   *                  {@link IMappableContainer}s the given extractor returns.
   * @return a new {@link IMappableContainer} with the elements of the
   *         {@link IMappableContainer}s the given extractor extracts from the
   *         elements of the current {@link IMappableContainer}.
   */
  <E2> IContainer<E2> toFromGroups(Function<E, IContainer<E2>> extractor);

  /**
   * @param norm
   * @return a new array with the ints the given norm returns from the elements of
   *         the current {@link IMappableContainer}. Maps null elements to 0.
   */
  int[] toIntArray(ToIntFunction<E> norm);

  /**
   * @param norm
   * @return a new array with the longs the given norm returns from the elements
   *         of the current {@link IMappableContainer}. Maps null elements to 0.
   */
  long[] toLongArray(ToLongFunction<E> norm);
}
