//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

//interface
/**
 * A {@link ICardinalityHolder} has a {@link Cardinality}.
 * 
 * @author Silvan Wyss
 * @date 2023-08-25
 */
public interface ICardinalityHolder {

  //method declaration
  /**
   * @return the {@link Cardinality} of the current {@link ICardinalityHolder}.
   */
  Cardinality getCardinality();
}
