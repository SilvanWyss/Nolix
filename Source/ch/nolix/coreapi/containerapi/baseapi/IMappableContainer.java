//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

//interface
/**
 * @author Silvan Wyss
 * @date 2023-10-14
 * @param <E> is the type of the elements a {@link IMappableContainer}.
 */
public interface IMappableContainer<E> {

  // method declaration
  /**
   * @param extractor
   * @param <E2>      is the type of the elements the given extractor returns.
   * @return a new {@link IMappableContainer} with the elements the given
   *         extractor extracts from the elements of the current
   *         {@link IMappableContainer}.
   */
  <E2> IContainer<E2> to(Function<E, E2> extractor);

  // method declaration
  /**
   * @return a new array with the elements of the current
   *         {@link IMappableContainer}.
   */
  Object[] toArray();

  // method declaration
  /**
   * @param byteGetter
   * @return a new array with the bytes the given byteGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  byte[] toByteArray(Function<E, Byte> byteGetter);

  // method declaration
  /**
   * @param charGetter
   * @return a new array with the chars the given charGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  char[] toCharArray(Function<E, Character> charGetter);

  // method declaration
  /**
   * @return a concatenated {@link String} representation of the current
   *         {@link IMappableContainer}.
   */
  String toConcatenatedString();

  // method declaration
  /**
   * @param doubleGetter
   * @return a new array with the doubles the given doubleGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  double[] toDoubleArray(ToDoubleFunction<E> doubleGetter);

  // method declaration
  /**
   * @param extractor
   * @param <E2>      is the type of the elements of the
   *                  {@link IMappableContainer}s the given extractor returns.
   * @return a new {@link IMappableContainer} with the elements of the
   *         {@link IMappableContainer}s the given extractor extracts from the
   *         elements of the current {@link IMappableContainer}.
   */
  <E2> IContainer<E2> toFromGroups(Function<E, IContainer<E2>> extractor);

  // method declaration
  /**
   * @param intGetter
   * @return a new array with the ints the given intGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  int[] toIntArray(ToIntFunction<E> intGetter);

  // method declaration
  /**
   * @param longGetter
   * @return a new array with the longs the given longGetter returns from the
   *         elements of the current {@link IMappableContainer}.
   */
  long[] toLongArray(ToLongFunction<E> longGetter);
}
