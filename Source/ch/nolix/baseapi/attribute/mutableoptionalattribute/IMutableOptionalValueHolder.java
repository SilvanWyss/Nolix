/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalValueHolder;

/**
 * A {@link IMutableOptionalValueHolder} is a {@link IOptionalValueHolder} whose
 * value can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IMutableOptionalValueHolder}
 */
public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {
  /**
   * Removes the value of the current {@link IMutableOptionalValueHolder}.
   */
  void removeValue();

  /**
   * Sets the value of the current {@link IMutableOptionalValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is blank
   */
  void setValue(V value);
}
