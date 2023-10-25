//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalIndexHolder} can have an index.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface IOptionalIndexHolder {

  //method declaration
  /**
   * @return the index of the current {@link IOptionalIndexHolder}.
   * @throws RuntimeException if the current {@link IOptionalIndexHolder} does not have
   *                          an index.
   */
  String getIndex();

  //method declaration
  /**
   * @return true if the current {@link IOptionalIndexHolder} has an index.
   */
  boolean hasIndex();

  //method declaration
  /**
   * @param index
   * @return true if the current {@link IOptionalIndexHolder} has the given index.
   */
  boolean hasIndex(String index);
}
