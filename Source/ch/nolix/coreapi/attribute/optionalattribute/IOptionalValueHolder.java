/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalValueHolder} can have a value.
 * 
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link IOptionalValueHolder}.
 */
public interface IOptionalValueHolder<V> {
  /**
   * @return the value of the current {@link IOptionalValueHolder}.
   * @throws RuntimeException if the current {@link IOptionalValueHolder} does not
   *                          have a value.
   */
  V getValue();

  /**
   * @return true if the current {@link IOptionalValueHolder} has a value, false
   *         otherwise.
   */
  boolean hasValue();
}
