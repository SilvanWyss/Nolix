//package declaration
package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiValueHolder;

//interface
/**
 * A {@link IMutableMultiValueHolder} is a {@link IMultiValueHolder} whose
 * values can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-09-11
 * @param <V> is the type of the values of a {@link IMutableMultiValueHolder}.
 */
public interface IMutableMultiValueHolder<V> extends IMultiValueHolder<V> {

  //method declaration
  /**
   * Adds the given value to the current {@link IMutableMultiValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null.
   */
  void addValue(V value);

  //method declaration
  /**
   * Removes the given value from the current {@link IMutableMultiValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the current {@link IMutableMultiValueHolder} does
   *                          not contain the given value.
   */
  void removeValue(V value);

  //method declaration
  /**
   * Removes all values from the current {@link IMutableMultiValueHolder}.
   */
  void removeValues();
}
