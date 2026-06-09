/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link IValueHolder} contains a value.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IValueHolder}
 */
public interface IValueHolder<V> {
  /**
   * @return the value of the current {@link IValueHolder}
   */
  V getStoredValue();
}
