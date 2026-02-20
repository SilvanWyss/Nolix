/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalValueHolder;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link IMutableOptionalValueHolder}.
 */
public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {
  void removeValue();

  void setValue(V value);
}
