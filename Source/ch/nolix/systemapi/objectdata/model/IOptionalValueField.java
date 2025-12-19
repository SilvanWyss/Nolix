package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 */
public interface IOptionalValueField<V> extends Clearable, IBaseValueField<V> {
  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
