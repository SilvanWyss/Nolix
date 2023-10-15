//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link IValueHolder} has a value.
 * 
 * @author Silvan Wyss
 * @date 2018-09-09
 * @param <V> is the type of the value of a {@link IValueHolder}.
 */
public interface IValueHolder<V> {

  // method declaration
  /**
   * @return the value of the current {@link IValueHolder}.
   */
  V getValue();
}
