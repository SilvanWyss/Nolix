package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.datamodelapi.cardinalityapi.BaseCardinality;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ICardinalityHolder} has a {@link Cardinality}.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 */
@AllowDefaultMethodsAsDesignPattern
public interface ICardinalityHolder {

  /**
   * @return the {@link Cardinality} of the current {@link ICardinalityHolder}.
   */
  Cardinality getCardinality();

  /**
   * @return the {@link BaseCardinality} of the current
   *         {@link ICardinalityHolder}.
   */
  default BaseCardinality getBaseCardinality() {
    return getCardinality().getBaseCardinality();
  }
}
