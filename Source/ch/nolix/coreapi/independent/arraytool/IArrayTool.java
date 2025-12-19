package ch.nolix.coreapi.independent.arraytool;

/**
 * A {@link IArrayTool} provides functions to handle arrays.
 * 
 * @author Silvan Wyss
 */
public interface IArrayTool {
  /**
   * @param element
   * @param elements
   * @param <E>      is the type of the given element and the given elements.
   * @return a new array with the given element and elements.
   * @throws RuntimeException if the given elements is null.
   */
  <E> E[] createArrayWithElement(E element, @SuppressWarnings("unchecked") E... elements);

  /**
   * @param bytes
   * @return a new {@link Iterable} with the given bytes.
   * @throws RuntimeException if the given bytes is null.
   */
  Iterable<Byte> createIterable(byte[] bytes);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values.
   * @throws RuntimeException if the given values is null.
   */
  Iterable<Double> createIterable(double[] values);

  /**
   * @param elements
   * @param <E>      is the type of the given elements.
   * @return a new {@link Iterable} with the given elements.
   * @throws RuntimeException if the given elements is null.
   */
  <E> Iterable<E> createIterable(final E[] elements);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values.
   * @throws RuntimeException if the given values is null.
   */
  Iterable<Long> createIterable(int[] values);

  /**
   * @param values
   * @return a new {@link Iterable} with the given values.
   * @throws RuntimeException if the given values is null.
   */
  Iterable<Long> createIterable(long[] values);

  /**
   * @param values
   * @return a {@link String} representation of the given values.
   * @throws RuntimeException if the given values is null.
   */
  String createString(long[] values);
}
