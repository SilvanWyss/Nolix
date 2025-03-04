package ch.nolix.systemapi.objectdataapi.fieldapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractValue;

public interface IOptionalValue<V> extends Clearable, IAbstractValue<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
