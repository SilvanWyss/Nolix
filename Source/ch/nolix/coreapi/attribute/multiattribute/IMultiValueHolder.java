package ch.nolix.coreapi.attribute.multiattribute;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link IMultiValueHolder} can contain several values.
 * 
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link IMultiValueHolder}.
 */
public interface IMultiValueHolder<V> {
  /**
   * @return the values of the current {@link IMultiValueHolder}.
   */
  IContainer<V> getStoredValues();
}
