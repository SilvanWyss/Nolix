package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IOptionalValue<V> extends Clearable, IAbstractValue<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
