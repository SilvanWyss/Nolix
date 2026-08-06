/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IValueField}.
 */
public interface IValueField<V> extends BaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
