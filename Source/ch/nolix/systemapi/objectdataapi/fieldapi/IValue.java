package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractValue;

public interface IValue<V> extends IAbstractValue<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
