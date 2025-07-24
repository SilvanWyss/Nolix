package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IOptionalValueField<V> extends Clearable, IAbstractValueField<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
