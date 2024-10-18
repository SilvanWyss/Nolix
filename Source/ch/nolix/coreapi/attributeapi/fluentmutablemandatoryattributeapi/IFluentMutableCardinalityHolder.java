package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ICardinalityHolder;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

/**
 * A {@link IFluentMutableCardinalityHolder} is a {@link ICardinalityHolder}
 * whose {@link Cardinality} can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 * @param <FMCH> is the type of a {@link IFluentMutableCardinalityHolder}.
 */
public interface IFluentMutableCardinalityHolder<FMCH extends IFluentMutableCardinalityHolder<FMCH>>
extends ICardinalityHolder {

  /**
   * Sets the {@link Cardinality} of the current
   * {@link IFluentMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link IFluentMutableCardinalityHolder}.
   * @throws RuntimeException if the given cardinality is null.
   */
  FMCH setCardinality(Cardinality cardinality);
}
