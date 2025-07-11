package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.ICardinalityHolder;

/**
 * A {@link IMutableCardinalityHolder} is a {@link ICardinalityHolder} whose
 * cardinality can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-24
 */
public interface IMutableCardinalityHolder extends ICardinalityHolder {

  /**
   * Sets the cardinality of the current {@link IMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null.
   */
  void setCardinality(String cardinality);
}
