//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalIndexed} can have an index.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalIndexed {

  // method declaration
  /**
   * @return the index of the current {@link OptionalIndexed}.
   * @throws RuntimeException if the current {@link OptionalIndexed} does not have
   *                          an index.
   */
  String getIndex();

  // method declaration
  /**
   * @return true if the current {@link OptionalIndexed} has an index.
   */
  boolean hasIndex();

  // method declaration
  /**
   * @param index
   * @return true if the current {@link OptionalIndexed} has the given index.
   */
  boolean hasIndex(String index);
}
