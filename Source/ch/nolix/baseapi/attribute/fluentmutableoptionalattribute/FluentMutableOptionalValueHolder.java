/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalValueHolder;

/**
 * A {@link FluentMutableOptionalValueHolder} is a {@link OptionalValueHolder}
 * whose value can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalValueHolder}
 * @param <V> the type of the value of a
 *            {@link FluentMutableOptionalValueHolder}
 */
public interface FluentMutableOptionalValueHolder<H extends FluentMutableOptionalValueHolder<H, V>, V>
extends OptionalValueHolder<V> {
  /**
   * Removes the value of the current {@link FluentMutableOptionalValueHolder}.
   */
  void removeValue();

  /**
   * Sets the value of the current {@link FluentMutableOptionalValueHolder}.
   * 
   * @param value
   * @return the current {@link FluentMutableOptionalValueHolder}
   * @throws RuntimeException if the given value is null
   */
  H setValue(String value);
}
