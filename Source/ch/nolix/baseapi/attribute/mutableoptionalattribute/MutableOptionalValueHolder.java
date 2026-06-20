/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalValueHolder;

/**
 * A {@link MutableOptionalValueHolder} is a {@link OptionalValueHolder} whose
 * value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link MutableOptionalValueHolder}
 */
public interface MutableOptionalValueHolder<V> extends OptionalValueHolder<V> {
  /**
   * Removes the value of the current {@link MutableOptionalValueHolder}.
   */
  void removeValue();

  /**
   * Sets the value of the current {@link MutableOptionalValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is blank
   */
  void setValue(V value);
}
