/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link IBaseValueField}.
 */
public interface IBaseValueField<V> extends IField {
  Class<V> getValueType();
}
