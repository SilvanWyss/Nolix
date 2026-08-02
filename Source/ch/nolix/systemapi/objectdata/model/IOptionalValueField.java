/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IOptionalValueField}.
 */
public interface IOptionalValueField<V> extends Clearable, IBaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
