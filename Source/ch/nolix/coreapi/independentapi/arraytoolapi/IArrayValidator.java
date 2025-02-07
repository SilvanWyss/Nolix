package ch.nolix.coreapi.independentapi.arraytoolapi;

/**
 * @author Silvan Wyss
 * @version 2024-12-14
 */
public interface IArrayValidator {

  /**
   * @param array
   * @throws RuntimeException if the given array is null.
   * @throws RuntimeException if the given array contains a null element.
   */
  void assertDoesNotContainNull(Object[] array);
}
