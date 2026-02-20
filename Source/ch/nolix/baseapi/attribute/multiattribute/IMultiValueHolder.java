/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.base.IContainer;

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
