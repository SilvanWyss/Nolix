//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiValueHolder;

//interface
/**
 * A {@link IFluentMutableMultiValueHolder} is a {@link IMultiValueHolder} whose
 * values can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-08-25
 * @param <FMMVH> is the type of a {@link IFluentMutableMultiValueHolder}.
 * @param <V>     is the type of the values of a
 *                {@link IFluentMutableMultiValueHolder}.
 */
public interface IFluentMutableMultiValueHolder<FMMVH extends IFluentMutableMultiValueHolder<FMMVH, V>, V>
extends IMultiValueHolder<V> {

  //method declaration
  /**
   * Adds the given value to the current {@link IFluentMutableMultiValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentMutableMultiValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  FMMVH addValue(V value);

  //method declaration
  /**
   * Removes the given value from the current
   * {@link IFluentMutableMultiValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the current
   *                          {@link IFluentMutableMultiValueHolder} does not
   *                          contain the given value.
   */
  void removeValue(V value);

  //method declaration
  /**
   * Removes all values from the current {@link IFluentMutableMultiValueHolder}.
   */
  void removeValues();
}
