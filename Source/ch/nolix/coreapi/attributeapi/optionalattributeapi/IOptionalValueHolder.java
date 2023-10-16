//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalValueHolder} can have a value.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 * @param <V> is the type of the value of a {@link IOptionalValueHolder}.
 */
public interface IOptionalValueHolder<V> {

  //method declaration
  /**
   * @return the value of the current {@link IOptionalValueHolder}.
   * @throws RuntimeException if the current {@link IOptionalValueHolder} does not
   *                          have a value.
   */
  V getValue();

  //method declaration
  /**
   * @return true if the current {@link IOptionalValueHolder} has a value.
   */
  boolean hasValue();
}
