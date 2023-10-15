//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;

//interface
/**
 * A {@link IFluentOptionalValueHolder} is a {@link IOptionalValueHolder} whose
 * value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 * @param <FOVH> is the type of a {@link IFluentOptionalValueHolder}.
 * @param <V>    is the type of the value of a
 *               {@link IFluentOptionalValueHolder}.
 */
public interface IFluentOptionalValueHolder<FOVH extends IFluentOptionalValueHolder<FOVH, V>, V>
    extends IOptionalValueHolder<V> {

  // method declaration
  /**
   * Removes the value of the current {@link IFluentOptionalValueHolder}.
   * 
   * @return the current {@link IFluentOptionalValueHolder}.
   */
  FOVH removeValue();

  // method declaration
  /**
   * Sets the value of the current {@link IFluentOptionalValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentOptionalValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  FOVH setValue(String value);
}
