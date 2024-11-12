package ch.nolix.coreapi.containerapi.commoncontainerapi;

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
   * @param byteGetter
   * @return a new array with the bytes the given byteGetter returns from the
   *         elements of the current {@link ArrayMappable}.
   */
  byte[] toByteArray(Function<E, Byte> byteGetter);

  /**
   * @param charGetter
   * @return a new array with the chars the given charGetter returns from the
   *         elements of the current {@link ArrayMappable}.
   */
  char[] toCharArray(Function<E, Character> charGetter);

  /**
   * @param doubleGetter
   * @return a new array with the doubles the given doubleGetter returns from the
   *         elements of the current {@link ArrayMappable}.
   */
  double[] toDoubleArray(ToDoubleFunction<E> doubleGetter);

  /**
   * @param norm
   * @return a new array with the ints the given norm returns from the elements of
   *         the current {@link ArrayMappable}. Maps null elements to 0.
   */
  int[] toIntArray(ToIntFunction<E> norm);

  /**
   * @param norm
   * @return a new array with the longs the given norm returns from the elements
   *         of the current {@link ArrayMappable}. Maps null elements to 0.
   */
  long[] toLongArray(ToLongFunction<E> norm);

  /**
   * @return a new array with the {@link String} representations of the elements
   *         of the current {@link ArrayMappable}.
   */
  String[] toStringArray();
}
