/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link IOptionalValueHolder} can contain a value.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IOptionalValueHolder}
 */
public interface IOptionalValueHolder<V> {
  /**
   * @return the value of the current {@link IOptionalValueHolder}
   * @throws RuntimeException if the current {@link IOptionalValueHolder} does not
   *                          contain a value
   */
  V getStoredValue();

  /**
   * @return true if the current {@link IOptionalValueHolder} contains a value,
   *         false otherwise
   */
  boolean hasValue();
}
