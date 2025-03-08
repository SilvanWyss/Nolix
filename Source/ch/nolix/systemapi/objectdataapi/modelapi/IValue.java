package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IValue<V> extends IAbstractValue<V> {

  V getStoredValue();

  void setValue(V value);

  void setValueFromString(String string);
}
