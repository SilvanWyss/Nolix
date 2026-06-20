/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalValueHolder} can contain a value.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link OptionalValueHolder}
 */
public interface OptionalValueHolder<V> {
  /**
   * @return the value of the current {@link OptionalValueHolder}
   * @throws RuntimeException if the current {@link OptionalValueHolder} does not
   *                          contain a value
   */
  V getStoredValue();

  /**
   * @return true if the current {@link OptionalValueHolder} contains a value,
   *         false otherwise
   */
  boolean hasValue();
}
