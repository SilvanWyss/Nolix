//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ICardinalityHolder;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

//interface
/**
 * A {@link IFluentMutableCardinalityHolder} is a {@link ICardinalityHolder}
 * whose {@link Cardinality} can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-08-25
 * @param <FMCH> is the type of a {@link IFluentMutableCardinalityHolder}.
 */
public interface IFluentMutableCardinalityHolder<FMCH extends IFluentMutableCardinalityHolder<FMCH>>
    extends ICardinalityHolder {

  //method declaration
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
