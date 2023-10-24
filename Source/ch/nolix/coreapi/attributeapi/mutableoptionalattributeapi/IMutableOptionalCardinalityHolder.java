//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalCardinalityHolder;

//interface
/**
 * A {@link IMutableOptionalCardinalityHolder} is a
 * {@link IOptionalCardinalityHolder} whose cardinality can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-10-24
 */
public interface IMutableOptionalCardinalityHolder extends IOptionalCardinalityHolder {

  //method declaration
  /**
   * Removes the cardinality of the current
   * {@link IMutableOptionalCardinalityHolder}.
   */
  void removeCardinality();

  //method declaration
  /**
   * Sets the cardinality of the current
   * {@link IMutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null.
   */
  void setCardinality(String cardinality);
}
