package ch.nolix.coreapi.attributeapi.multiattributeapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * A {@link IMultiValueHolder} can contain several values.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 * @param <V> is the type of the values of a {@link IMultiValueHolder}.
 */
public interface IMultiValueHolder<V> {

  /**
   * @return the values of the current {@link IMultiValueHolder}.
   */
  IContainer<V> getStoredValues();
}
