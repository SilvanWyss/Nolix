package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalOneBasedIndexHolder} can have a one-based index.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalOneBasedIndexHolder {
  /**
   * @return the one-based index of the current
   *         {@link IOptionalOneBasedIndexHolder}.
   * @throws RuntimeException if the current {@link IOptionalOneBasedIndexHolder}
   *                          does not have a one-based index.
   */
  int getOneBasedIndex();

  /**
   * @return true if the current {@link IOptionalOneBasedIndexHolder} has a
   *         one-based index, false otherwise.
   */
  boolean hasOneBasedIndex();
}
