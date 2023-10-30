//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.BaseCardinality;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ICardinalityHolder} has a {@link Cardinality}.
 * 
 * @author Silvan Wyss
 * @date 2023-08-25
 */
@AllowDefaultMethodsAsDesignPattern
public interface ICardinalityHolder {

  //method declaration
  /**
   * @return the {@link Cardinality} of the current {@link ICardinalityHolder}.
   */
  Cardinality getCardinality();

  //method
  /**
   * @return the {@link BaseCardinality} of the current
   *         {@link ICardinalityHolder}.
   */
  default BaseCardinality getBaseCardinality() {
    return getCardinality().getBaseCardinality();
  }
}
