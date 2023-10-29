//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;

//interface
/**
 * A {@link IFluentMutableOptionalValueHolder} is a {@link IOptionalValueHolder}
 * whose value can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 * @param <FMOVH> is the type of a {@link IFluentMutableOptionalValueHolder}.
 * @param <V>     is the type of the value of a
 *                {@link IFluentMutableOptionalValueHolder}.
 */
public interface IFluentMutableOptionalValueHolder<FMOVH extends IFluentMutableOptionalValueHolder<FMOVH, V>, V>
extends IOptionalValueHolder<V> {

  //method declaration
  /**
   * Removes the value of the current {@link IFluentMutableOptionalValueHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalValueHolder}.
   */
  FMOVH removeValue();

  //method declaration
  /**
   * Sets the value of the current {@link IFluentMutableOptionalValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentMutableOptionalValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  FMOVH setValue(String value);
}
