/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link BaseValueField}.
 */
public interface BaseValueField<V> extends Field {
  Class<V> getValueType();
}
