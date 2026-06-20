/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link ValueHolder} contains a value.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link ValueHolder}
 */
public interface ValueHolder<V> {
  /**
   * @return the value of the current {@link ValueHolder}
   */
  V getStoredValue();
}
