//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ICardinalityHolder;

//interface
/**
 * A {@link IFluentMutableOptionalCardinalityHolder} is a
 * {@link ICardinalityHolder} whose cardinality can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-10-23
 * @param <FMOCH> is the type of a
 *                {@link IFluentMutableOptionalCardinalityHolder}.
 */
public interface IFluentMutableOptionalCardinalityHolder<FMOCH extends IFluentMutableOptionalCardinalityHolder<FMOCH>>
    extends ICardinalityHolder {

  //method declaration
  /**
   * Removes the cardinality of the current
   * {@link IFluentMutableOptionalCardinalityHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalCardinalityHolder}.
   */
  FMOCH removeCardinality();

  //method declaration
  /**
   * Sets the cardinality of the current
   * {@link IFluentMutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link IFluentMutableOptionalCardinalityHolder}.
   * @throws RuntimeException if the given cardinality is null.
   */
  FMOCH setCardinality(String cardinality);
}
