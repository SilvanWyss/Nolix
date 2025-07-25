package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.state.statemutation.Clearable;

public interface IOptionalValueField<V> extends Clearable, IAbstractValueField<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
