/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link IValueHolder} has a value.
 * 
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link IValueHolder}.
 */
public interface IValueHolder<V> {
  /**
   * @return the value of the current {@link IValueHolder}.
   */
  V getValue();
}
