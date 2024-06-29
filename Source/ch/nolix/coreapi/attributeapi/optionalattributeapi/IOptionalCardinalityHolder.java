//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalCardinalityHolder} can have a cardinality.
 * 
 * @author Silvan Wyss
 * @version 2023-10-24
 */
public interface IOptionalCardinalityHolder {

  //method declaration
  /**
   * @return the cardinality of the current {@link IOptionalCardinalityHolder}.
   * @throws RuntimeException if the current {@link IOptionalCardinalityHolder}
   *                          does not have a cardinality.
   */
  String getCardinality();

  //method declaration
  /**
   * @return true if the current {@link IOptionalCardinalityHolder} has a
   *         cardinality.
   */
  boolean hasCardinality();
}
