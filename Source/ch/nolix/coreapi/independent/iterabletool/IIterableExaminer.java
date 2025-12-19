package ch.nolix.coreapi.independent.iterabletool;

/**
 * @author Silvan Wyss
 */
public interface IIterableExaminer {
  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element, false
   *         otherwise.
   */
  boolean containsElement(Iterable<?> iterable, Object element);

  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element multiple times,
   *         false otherwise.
   */
  boolean containsElementMultipleTimes(Iterable<?> iterable, Object element);

  /**
   * @param iterable
   * @param element
   * @return true if the given iterable contains the given element exactly 1 time,
   *         false otherwise.
   */
  boolean containsElementOnce(Iterable<?> iterable, Object element);

  /**
   * @param iterable
   * @param stringRepresentation
   * @return true if the given iterable contains exactly 1 element with the given
   *         stringRepresentation, false otherwise.
   */
  boolean containsExactlyOneWithStringRepresentation(Iterable<?> iterable, String stringRepresentation);

  /**
   * @param iterable
   * @return true if the given iterable is empty, false otherwise.
   * @throws RuntimeException if the given iterable is null.
   */
  boolean isEmpty(Iterable<?> iterable);
}
