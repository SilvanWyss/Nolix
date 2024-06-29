//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ICardinalityHolder;

//interface
/**
 * A {@link IMutableCardinalityHolder} is a {@link ICardinalityHolder} whose
 * cardinality can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-24
 */
public interface IMutableCardinalityHolder extends ICardinalityHolder {

  //method declaration
  /**
   * Sets the cardinality of the current {@link IMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null.
   */
  void setCardinality(String cardinality);
}
